package com.lopsa.kotlinapp.services

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by ADMIN on 29/11/2017.
 */
class ConexionNetwordService {

    //esta funcioon es para ver si hay conexion a internet
    fun compruebaConexion(context: Context): Boolean {
        var connected = false
        val connec = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // Recupera todas las redes (tanto móviles como wifi)
        val redes = connec.allNetworkInfo

        for (i in redes.indices) {
            // Si alguna red tiene conexión, se devuelve true
            if (redes[i].state == NetworkInfo.State.CONNECTED) {
                connected = true
            }
        }
        return connected
    }
}