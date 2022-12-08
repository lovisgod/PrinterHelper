package com.lovisgod.printerhelper.utils

import android.R
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.widget.ScrollView
import android.widget.Toast


object ToastUtils {

    fun showLong(message: String, context: Context) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }




    /**
     * Print receipt of the transaction*/
    fun getScreenBitMap(activity: Activity, view: ViewGroup): Bitmap? {
        var rootview = view

        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)

        //  this get the width of the layout even beyond the visible screen
        var width = view.getChildAt(0).width

        // this will get the height of the layout even beyond the visible screen
        var height = view.getChildAt(0).height
        // Create a mutable bitmap

        // Create a mutable bitmap
        val secondScreen = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        // Created a canvas using the bitmap
        val c = Canvas(secondScreen)

        val bgDrawable: Drawable? = view.background
        if (bgDrawable != null) bgDrawable.draw(c) else c.drawColor(Color.WHITE)
        rootview.draw(c)
        return secondScreen
    }

    fun loadBitmapFromView(v: View): Bitmap? {

        val w = if (v is ScrollView) v.getChildAt(0).width else v.width
        val h = if (v is ScrollView) v.getChildAt(0).height else v.height
        val b = Bitmap.createBitmap(
            w,
            h,
            Bitmap.Config.ARGB_8888
        )
        val c = Canvas(b)

        val bgDrawable: Drawable? = v.background
        if (bgDrawable != null) bgDrawable.draw(c) else c.drawColor(Color.WHITE)
        v.layout(v.left, v.top, v.right, v.bottom)
        v.draw(c)
        return b
    }


     fun renderOffScreen(activity: Activity, layout: Int, parent: ViewGroup): Bitmap? {

        // Getting width, height device
        val displaymetrics = DisplayMetrics()
//         activity.windowManager.defaultDisplay.getMetrics(displaymetrics)
        val display = activity.windowManager.defaultDisplay
        val width: Int = display.width
        val height: Int = display.height
        Log.i("offscreenprint", "Width- $width Height - $height")

        // Create a mutable bitmap
        val secondScreen = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        // Created a canvas using the bitmap
        val c = Canvas(secondScreen)

        // Inflated the second screen

         val viewxInflater: LayoutInflater = parent.getContext()
             .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

         val viewX = viewxInflater.inflate(layout, parent, false)
//        val inflater = LayoutInflater.from(activity.baseContext)
//        val secondView: View = inflater.inflate(layout, null, false)

        viewX.layoutParams = ViewGroup.LayoutParams(
            displaymetrics.widthPixels,
            displaymetrics.heightPixels / 3
        )

        Log.i(
            "offcreenprint",
            "secondView.Height- " + viewX.height + " , secondView.Width " + viewX.width
        )

         // set layout background to be white
         val bgDrawable: Drawable? = viewX.background
         if (bgDrawable != null) bgDrawable.draw(c) else c.drawColor(Color.WHITE)
        // Drawn the inflated view to canvas
         viewX.layout(viewX.left, viewX.top, viewX.right, viewX.bottom)
        viewX.draw(c)
        return secondScreen
    }
}