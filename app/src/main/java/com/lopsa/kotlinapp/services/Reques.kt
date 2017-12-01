package com.lopsa.kotlinapp.services

import android.util.Log
import java.net.URL

/**
 * Created by ADMIN on 30/11/2017.
 */
class Reques(val url: String) {

    fun run() {
        val tiempoJsonStr = URL(url).readText()
        Log.d(javaClass.simpleName, tiempoJsonStr)
    }
}