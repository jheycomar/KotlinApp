package Util

import org.json.JSONException
import org.json.JSONObject

/**
 * Created by ADMIN on 30/11/2017.
 */
object Utils {
    val BASE_URL="http://api.openweathermap.org/data/2.5/weather?q="
    val ICON_URL="http://openweathermap.org/img/w/"

   @Throws(JSONException::class)
    fun  getObjet(tagName: String,jsonObject: JSONObject):JSONObject{
        val jobj=jsonObject.getJSONObject(tagName)
        return jobj
    }

    @Throws(JSONException::class)
    fun  getString(tagName: String,jsonObject: JSONObject):String{
        return jsonObject.getString(tagName)
    }

    @Throws(JSONException::class)
    fun  getFloat(tagName: String,jsonObject: JSONObject):Float{
        return jsonObject.getDouble(tagName).toFloat()
    }
    @Throws(JSONException::class)
    fun  getDoble(tagName: String,jsonObject: JSONObject):Double{
        return jsonObject.getDouble(tagName)
    }
    @Throws(JSONException::class)
    fun  getInt(tagName: String,jsonObject: JSONObject):Int{
        return jsonObject.getInt(tagName)
    }
    @Throws(JSONException::class)
    fun  getLong(tagName: String,jsonObject: JSONObject):Long{
        return jsonObject.getLong(tagName)
    }
}