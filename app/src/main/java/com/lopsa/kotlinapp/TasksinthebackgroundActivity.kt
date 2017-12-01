package com.lopsa.kotlinapp

import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.lopsa.kotlinapp.services.ConexionNetwordService
import kotlinx.android.synthetic.main.activity_tasksinthebackground.*
import org.jetbrains.anko.*
import org.jetbrains.anko.design.snackbar
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class TasksinthebackgroundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasksinthebackground)

        val helper= ConexionNetwordService()
        var conexion =helper.compruebaConexion(this)
        if (conexion==true){
            //si hay internet carga la foto sino muestra un mensage
            var dialog:Dialog
            dialog=indeterminateProgressDialog("cargando...","Descargando Imagen")
            dialog.show()
            doAsync {
                val image =downloadImage("https://2.bp.blogspot.com/-Er7NBOTjqKU/WR4NWkktGsI/AAAAAAAAEIc/jflA_5QfWV0QTd0QGqRRgTqgRExUlwhnQCLcB/s1600/curso-gratis-kotlin.png")
                uiThread {
                    imgView.setImageBitmap(image)
                    dialog.dismiss()

                }

            }

        }else{

            longToast("Berifique su conexion a Internet")
        }

    }
    //funcion o metodo  que carga la imagen
    fun downloadImage(imageHttpAddress: String):Bitmap?{
        var imageUri:URL?=null
        var image:Bitmap?=null
        try {
            imageUri=URL(imageHttpAddress)
            val conn=imageUri.openConnection() as HttpURLConnection
            conn.connect()
            image=BitmapFactory.decodeStream(conn.getInputStream())


        }catch (ex:IOException){
            ex.printStackTrace()
        }
        return image
    }


}
