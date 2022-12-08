package com.lovisgod.printerhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.lovisgod.printerhelper.pax.PrinterFace
import com.lovisgod.printerhelper.utils.ToastUtils

class MainActivity : AppCompatActivity() {

    lateinit var printBtn: Button
    lateinit var tttttt: TextView
    lateinit var xxxx: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        xxxx = findViewById(R.id.vvv)
        tttttt = findViewById(R.id.ttt)
        printBtn = findViewById<Button>(R.id.printButton)


        PrinterFace.init()

        println("info: printer status::: ${PrinterFace.getStatus()}")


        printBtn.setOnClickListener {
            val bitmap = ToastUtils.loadBitmapFromView(xxxx)
            PrinterFace.printBitmap(bitmap)
        }
    }

}