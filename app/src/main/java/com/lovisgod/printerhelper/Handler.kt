package com.lovisgod.printerhelper

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.pax.dal.IDAL
import com.pax.neptunelite.api.NeptuneLiteUser

object Handler {
    private var dal: IDAL? = null
    private var appContext: Context? = null


    fun getInstance(appContext: Context) {
        this.appContext = appContext

    }


    fun getPaxDal(): IDAL? {
        if (dal == null) {
            try {
                val start = System.currentTimeMillis()
                dal = NeptuneLiteUser.getInstance().getDal(appContext)
                Log.i("Test", "get dal cost:" + (System.currentTimeMillis() - start) + " ms")
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(
                   appContext,
                    "error occurred,DAL is null.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        return dal
    }
}