package Data

import Model.Clima
import Model.Lugar
import Util.Utils
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by ADMIN on 01/12/2017.
 */
object JSONParseClima {
    fun getWeather(data:String):Clima?{
        val clima=Clima()
        try {
            val jsonObject = JSONObject(data)

            val  lugar=Lugar()

            val coorObj=Utils.getObjet( "coord",jsonObject)
            lugar.lat=Utils.getFloat("lat",coorObj )
            lugar.long=Utils.getFloat("lon",coorObj)

            val sysObj=Utils.getObjet("sys",jsonObject)
            lugar.pais=Utils.getString("country",sysObj)
            lugar.ultimaActualizacion=Utils.getLong("dt",jsonObject)
            lugar.amanecer=Utils.getLong("sunrise",sysObj)
            lugar.puestaSol=Utils.getLong("sunset",sysObj)
            lugar.cuidad=Utils.getString("name",jsonObject)
            clima.lugar=lugar

            val mainObj=Utils.getObjet("main",jsonObject)
            clima.condicionActual.humedad=Utils.getFloat("humidity",mainObj)
            clima.condicionActual.temperatura=Utils.getDoble("temp",mainObj)
            clima.condicionActual.presicion=Utils.getFloat("pressure",mainObj)
            clima.condicionActual.maxTemp=Utils.getFloat("temp_max",mainObj)
            clima.condicionActual.minTemp=Utils.getFloat("temp_min",mainObj)

            val  jsonArray=jsonObject.getJSONArray("weather")
            val jsonWeather=jsonArray.getJSONObject(0)
            clima.condicionActual.weatherId=Utils.getInt("id",jsonWeather)
            clima.condicionActual.descripcion=Utils.getString("description",jsonWeather)
            clima.condicionActual.condicion=Utils.getString("main",jsonWeather)
            clima.condicionActual.icono=Utils.getString("icon",jsonWeather)

            val vientoObj=Utils.getObjet("wind",jsonObject)
            clima.viento.velocidad=Utils.getFloat("speed",vientoObj)
            clima.viento.cent=Utils.getFloat("deg",vientoObj)

            val  nubeObj=Utils.getObjet("clouds",jsonObject)
            clima.nubes.precipitacion=Utils.getInt("all",nubeObj)
            return clima

        }catch (e: JSONException){
            e.printStackTrace()
        }
        return  null
    }
}