package Model

/**
 * Created by ADMIN on 30/11/2017.
 */
data class Clima (var lugar:Lugar?=null,
                  var icon:String?=null,
                  var condicionActual:CondicionActual= CondicionActual(),
                  var temperatura:Temperatura= Temperatura(),
                  var viento: Viento= Viento(),
                  var nieve:Nieve= Nieve(),
                  var nubes:Nubes=Nubes())