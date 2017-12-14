package com.lopsa.kotlinapp

import Data.HttpClientClima
import Data.JSONParseClima
import Model.Clima
import Util.Utils
import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.icu.text.DateFormat
import android.icu.text.DecimalFormat
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.lopsa.kotlinapp.services.INetwordConneccion
import kotlinx.android.synthetic.main.activity_places.*
import org.apache.http.HttpStatus
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.longToast
import java.io.IOException
import java.io.InputStream
import java.util.*


class PlacesActivity : AppCompatActivity() {

    private var editText: EditText? = null
    var clima=Clima()
    val apikey="10fa5b19c69520dedfd5bf32de5a66fc"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places)
        //flecha atras
      supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val helper= INetwordConneccion()
        var conexion =helper.compruebaConexion(this)
        if (conexion==true){
            //si hay internet carga la foto sino muestra un mensage
            var dialog: Dialog
            dialog=indeterminateProgressDialog("cargando...","Descargando Imagen")
            dialog.show()
            renderClimaDatos("Cochabamba,BO")
            dialog.dismiss()

        }else{

            longToast("Berifique su conexion a Internet")
        }
       // renderClimaDatos("Merida,MX")

    }

    private inner class DescargarImagenAsync:AsyncTask<String,Void,Bitmap>(){
        override fun doInBackground(vararg params: String?): Bitmap {

            return descargarImagen(params[0] as String)
        }

        override fun onPostExecute(result: Bitmap?) {
            //super.onPostExecute(result)
            imgViewIcon.setImageBitmap(result)
        }
        fun descargarImagen(codigo:String):Bitmap{
            val cliente=DefaultHttpClient()
            val getRequest=HttpGet(Utils.ICON_URL+codigo+".png")
            try {
                val response=cliente.execute(getRequest)
                val statusCodigo=response.statusLine.statusCode
                if (statusCodigo!=HttpStatus.SC_OK){
                    Log.e("DescargarImagen","Error"+statusCodigo)
                    return null!!
                }
                val entry=response.entity
                if (entry !=null){
                    var inputStrean:InputStream?
                    inputStrean=entry.content
                    val bitmap:Bitmap=BitmapFactory.decodeStream(inputStrean)
                    return  bitmap
                }

            }catch (e:IOException){
                e.printStackTrace()
            }
            return null!!
        }

    }

    fun renderClimaDatos(ciudad:String){
        val climaTask=ClimaTask()
        climaTask.execute(*arrayOf(ciudad +"&APPID="+"10fa5b19c69520dedfd5bf32de5a66fc"+"&units=metric"))


    }
    private inner class ClimaTask:AsyncTask<String,Void,Clima>(){
        override fun doInBackground(vararg params: String?): Clima {

            val datos=HttpClientClima().getWeatherData(params[0])
            clima=JSONParseClima.getWeather(datos)!!
            clima.icon=clima.condicionActual.icono
            DescargarImagenAsync().execute(clima.icon)

            return  clima
        }

        override fun onPostExecute(result: Clima?) {
            super.onPostExecute(result)
            val formatoFecha= DateFormat.getTimeInstance()
            val amanecer= formatoFecha.format(Date(clima.lugar!!.amanecer))
            val puesta= formatoFecha.format(Date(clima.lugar!!.puestaSol))
            val actualizar= formatoFecha.format(Date(clima.lugar!!.ultimaActualizacion))

            val formatoDecimal = DecimalFormat("#.#")
            val formatoTemp=formatoDecimal.format(clima.condicionActual.temperatura)
            lblCiudad.text= clima.lugar!!.cuidad+","+clima.lugar!!.pais
            lblViewTemp.text=" "+formatoTemp+"ºC"
            lblUmedad.text= "Humedad: "+clima.condicionActual.humedad
            lblPresion.text="Presion: "+clima.condicionActual.presicion
            lblViento.text="Viento: "+clima.viento.velocidad+"mps"
            lblsunset.text="Puesta del sol: "+puesta
            lblsunrice.text="Amanecre: "+amanecer
            lblupdate.text=" Ultima actualizacion: "+actualizar
            lblNuve.text="Nuve: "+clima.condicionActual.condicion+"("+clima.condicionActual.descripcion+")"

        }

    }

}
