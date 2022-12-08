package com.lovisgod.printerhelper.pax

import android.graphics.Bitmap
import com.lovisgod.printerhelper.Handler
import com.lovisgod.printerhelper.Logger.LoggerHelper.logErr
import com.lovisgod.printerhelper.Logger.LoggerHelper.logTrue
import com.pax.dal.IPrinter
import com.pax.dal.entity.EFontTypeAscii
import com.pax.dal.entity.EFontTypeExtCode
import com.pax.dal.exceptions.PrinterDevException

object PrinterFace {
    private var printer: IPrinter? = null


    fun init() {
        try {
            printer = Handler.getPaxDal()?.printer
            printer!!.init()
            logTrue("init")
        } catch (e: PrinterDevException) {
            e.printStackTrace()
            logErr("init", e.toString())
        }
    }

    fun getStatus(): String? {
        return try {
            val status = printer!!.status
            logTrue("getStatus")
            statusCode2Str(status)
        } catch (e: PrinterDevException) {
            e.printStackTrace()
            logErr("getStatus", e.toString())
            ""
        }
    }

    fun fontSet(asciiFontType: EFontTypeAscii?, cFontType: EFontTypeExtCode?) {
        try {
            printer!!.fontSet(asciiFontType, cFontType)
            logTrue("fontSet")
        } catch (e: PrinterDevException) {
            e.printStackTrace()
            logErr("fontSet", e.toString())
        }
    }

    fun spaceSet(wordSpace: Byte, lineSpace: Byte) {
        try {
            printer!!.spaceSet(wordSpace, lineSpace)
            logTrue("spaceSet")
        } catch (e: PrinterDevException) {
            e.printStackTrace()
            logErr("spaceSet", e.toString())
        }
    }

    fun printStr(str: String?, charset: String?) {
        try {
            printer!!.printStr(str, charset)
           start()
            logTrue("printStr")
        } catch (e: PrinterDevException) {
            e.printStackTrace()
            logErr("printStr", e.toString())
        }
    }

    fun step(b: Int) {
        try {
            printer!!.step(b)
            logTrue("setStep")
        } catch (e: PrinterDevException) {
            e.printStackTrace()
            logErr("setStep", e.toString())
        }
    }

    fun printBitmap(bitmap: Bitmap?) {
        try {
            printer?.init()
            printer!!.printBitmap(bitmap)
            printer?.printStr("\n\n\n", null)
            start()
            logTrue("printBitmap")
        } catch (e: PrinterDevException) {
            e.printStackTrace()
            logErr("printBitmap", e.toString())
        }
    }

    fun start(): String? {
        return try {
            val res = printer!!.start()
            logTrue("start")
            statusCode2Str(res)
        } catch (e: PrinterDevException) {
            e.printStackTrace()
            logErr("start", e.toString())
            ""
        }
    }

    fun leftIndents(indent: Short) {
        try {
            printer!!.leftIndent(indent.toInt())
            logTrue("leftIndent")
        } catch (e: PrinterDevException) {
            e.printStackTrace()
            logErr("leftIndent", e.toString())
        }
    }

    fun getDotLine(): Int {
        return try {
            val dotLine = printer!!.dotLine
            logTrue("getDotLine")
            dotLine
        } catch (e: PrinterDevException) {
            e.printStackTrace()
            logErr("getDotLine", e.toString())
            -2
        }
    }

    fun setGray(level: Int) {
        try {
            printer!!.setGray(level)
            logTrue("setGray")
        } catch (e: PrinterDevException) {
            e.printStackTrace()
            logErr("setGray", e.toString())
        }
    }

    fun setDoubleWidth(isAscDouble: Boolean, isLocalDouble: Boolean) {
        try {
            printer!!.doubleWidth(isAscDouble, isLocalDouble)
            logTrue("doubleWidth")
        } catch (e: PrinterDevException) {
            e.printStackTrace()
            logErr("doubleWidth", e.toString())
        }
    }

    fun setDoubleHeight(isAscDouble: Boolean, isLocalDouble: Boolean) {
        try {
            printer!!.doubleHeight(isAscDouble, isLocalDouble)
            logTrue("doubleHeight")
        } catch (e: PrinterDevException) {
            e.printStackTrace()
            logErr("doubleHeight", e.toString())
        }
    }

    fun setInvert(isInvert: Boolean) {
        try {
            printer!!.invert(isInvert)
            logTrue("setInvert")
        } catch (e: PrinterDevException) {
            e.printStackTrace()
            logErr("setInvert", e.toString())
        }
    }

    fun cutPaper(mode: Int): String? {
        return try {
            printer!!.cutPaper(mode)
            logTrue("cutPaper")
            "cut paper successful"
        } catch (e: PrinterDevException) {
            e.printStackTrace()
            logErr("cutPaper", e.toString())
            e.toString()
        }
    }

    fun getCutMode(): String? {
        var resultStr = ""
        return try {
            val mode = printer!!.cutMode
            logTrue("getCutMode")
            when (mode) {
                0 -> resultStr = "Only support full paper cut"
                1 -> resultStr = "Only support partial paper cutting "
                2 -> resultStr = "support partial paper and full paper cutting "
                -1 -> resultStr = "No cutting knife,not support"
                else -> {}
            }
            resultStr
        } catch (e: PrinterDevException) {
            e.printStackTrace()
            logErr("getCutMode", e.toString())
            e.toString()
        }
    }

    fun statusCode2Str(status: Int): String? {
        var res = ""
        when (status) {
            0 -> res = "Success "
            1 -> res = "Printer is busy "
            2 -> res = "Out of paper "
            3 -> res = "The format of print data packet error "
            4 -> res = "Printer malfunctions "
            8 -> res = "Printer over heats "
            9 -> res = "Printer voltage is too low"
            240 -> res = "Printing is unfinished "
            252 -> res = " The printer has not installed font library "
            254 -> res = "Data package is too long "
            else -> {}
        }
        return res
    }
}