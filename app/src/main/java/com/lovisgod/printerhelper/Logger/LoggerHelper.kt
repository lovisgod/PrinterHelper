package com.lovisgod.printerhelper.Logger

import android.util.Log

object LoggerHelper {
        private var childName = ""
        fun logTrue(method: String) {
            childName = javaClass.simpleName + "."
            val trueLog = childName + method
            Log.i("IPPITest", trueLog)
        }

        fun logErr(method: String, errString: String) {
            childName = javaClass.simpleName + "."
            val errorLog = "$childName$method   errorMessage：$errString"
            Log.e("IPPITest", errorLog)
        }
}