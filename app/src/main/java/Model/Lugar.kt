package Model

/**
 * Created by ADMIN on 30/11/2017.
 */
data class  Lugar(var long:Float=0.toFloat(),var lat:Float=0.toFloat(),
                  var amanecer:Long=0,
                  var puestaSol:Long=0,
                  var  pais:String?= null,
                  var cuidad:String?=null,
                  var ultimaActualizacion:Long=0)