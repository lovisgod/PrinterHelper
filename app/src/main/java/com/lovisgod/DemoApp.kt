package com.lovisgod

import android.app.Application
import com.lovisgod.printerhelper.Handler

class DemoApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Handler.getInstance(applicationContext)
    }

}