package com.lopsa.kotlinapp

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_gato.*
import org.jetbrains.anko.backgroundDrawable
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

class GatoActivity : AppCompatActivity() {
    var jugador1= ArrayList<Int>()
    var jugador2=ArrayList<Int>()
    var jugadorActivo=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gato)

        btn1.setOnClickListener(object :View.OnClickListener {
            override fun onClick(v: View?) {
                if (v != null) {
                    btnCliqueado(v)
                }
            }

        })
        btn2.setOnClickListener(object :View.OnClickListener {
            override fun onClick(v: View?) {
                if (v != null) {
                    btnCliqueado(v)
                }
            }

        })
        btn3.setOnClickListener(object :View.OnClickListener {
            override fun onClick(v: View?) {
                if (v != null) {
                    btnCliqueado(v)
                }
            }

        })
        btn4.setOnClickListener(object :View.OnClickListener {
            override fun onClick(v: View?) {
                if (v != null) {
                    btnCliqueado(v)
                }
            }

        })
        btn5.setOnClickListener(object :View.OnClickListener {
            override fun onClick(v: View?) {
                if (v != null) {
                    btnCliqueado(v)
                }
            }

        })
        btn6.setOnClickListener(object :View.OnClickListener {
            override fun onClick(v: View?) {
                if (v != null) {
                    btnCliqueado(v)
                }
            }

        })
        btn7.setOnClickListener(object :View.OnClickListener {
            override fun onClick(v: View?) {
                if (v != null) {
                    btnCliqueado(v)
                }
            }

        })
        btn8.setOnClickListener(object :View.OnClickListener {
            override fun onClick(v: View?) {
                if (v != null) {
                    btnCliqueado(v)
                }
            }

        })

        btn9.setOnClickListener(object :View.OnClickListener {
            override fun onClick(v: View?) {
                if (v != null) {
                    btnCliqueado(v)
                }
            }

        })
        btnIniciar.onClick { nuevaPartida() }

    }

    fun btnCliqueado(view: View){
        val btnSelecionado= view as Button
        var idCelda=0
        lblTurno.setText("Turno:jugador $jugadorActivo")
        when(btnSelecionado.id){
        R.id.btn1 -> idCelda=1
        R.id.btn2 -> idCelda=2
        R.id.btn3 -> idCelda=3
        R.id.btn4 -> idCelda=4
        R.id.btn5 -> idCelda=5
        R.id.btn6 -> idCelda=6
        R.id.btn7 -> idCelda=7
        R.id.btn8 -> idCelda=8
        R.id.btn9 -> idCelda=9
        }
        partida(idCelda,btnSelecionado)
    }

    fun partida(idCelda:Int ,btnSelecionado:Button){
        if(jugadorActivo==1){
            btnSelecionado.text="X"
            btnSelecionado.setBackgroundColor(Color.YELLOW)
            jugador1.add(idCelda)
            jugadorActivo=2
        }
        else{
            btnSelecionado.text="0"
            btnSelecionado.setBackgroundColor(Color.GREEN)
            jugador2.add(idCelda)
            jugadorActivo=1
        }
        btnSelecionado.isEnabled=false
        ganador()
    }

    fun ganador(){
        var ganador=-1
        //linea 1
        if (jugador1.contains(1)&&jugador1.contains(2)&&jugador1.contains(3)){
            ganador=1
        }
        if (jugador2.contains(1)&&jugador2.contains(2)&&jugador2.contains(3)){
            ganador=2
        }
        //linea 2
        if (jugador1.contains(4)&&jugador1.contains(5)&&jugador1.contains(6)){
            ganador=1
        }
        if (jugador2.contains(4)&&jugador2.contains(5)&&jugador2.contains(6)){
            ganador=2
        }
        //linea 3
        if (jugador1.contains(7)&&jugador1.contains(8)&&jugador1.contains(9)){
            ganador=1
        }
        if (jugador2.contains(7)&&jugador2.contains(8)&&jugador2.contains(9)){
            ganador=2
        }
        //culum 1
        if (jugador1.contains(1)&&jugador1.contains(4)&&jugador1.contains(7)){
            ganador=1
        }
        if (jugador2.contains(1)&&jugador2.contains(4)&&jugador2.contains(7)){
            ganador=2
        }
        //colum 2
        if (jugador1.contains(2)&&jugador1.contains(5)&&jugador1.contains(8)){
            ganador=1
        }
        if (jugador2.contains(2)&&jugador2.contains(5)&&jugador2.contains(8)){
            ganador=2
        }
        //colum 3
        if (jugador1.contains(3)&&jugador1.contains(6)&&jugador1.contains(9)){
            ganador=1
        }
        if (jugador2.contains(3)&&jugador2.contains(6)&&jugador2.contains(9)){
            ganador=2
        }
        //lineaX 1
        if (jugador1.contains(1)&&jugador1.contains(5)&&jugador1.contains(9)){
            ganador=1
        }
        if (jugador2.contains(1)&&jugador2.contains(5)&&jugador2.contains(9)){
            ganador=2
        }
        //linea X2
        if (jugador1.contains(3)&&jugador1.contains(5)&&jugador1.contains(7)){
            ganador=1
        }
        if (jugador2.contains(3)&&jugador2.contains(5)&&jugador2.contains(7)){
            ganador=2
        }
        if (ganador==1){
            toast("¡jugador 1 ha ganado!")
        }
        if (ganador==2){
            toast("¡jugador 2 ha ganado!")
        }
    }

    fun nuevaPartida(){
         jugador1.clear()
         jugador2.clear()
         jugadorActivo=1
        lblTurno.setText("Turno:jugador $jugadorActivo")
        btn1.isEnabled=true
        btn1.text=""
        btn1.setBackgroundColor(R.drawable.transparente)
        btn2.isEnabled=true
        btn2.text=""
        btn2.setBackgroundColor(R.drawable.transparente)
        btn3.isEnabled=true
        btn3.text=""
        btn3.setBackgroundColor(R.drawable.transparente)
        btn4.isEnabled=true
        btn4.text=""
        btn4.setBackgroundColor(R.drawable.transparente)
        btn5.isEnabled=true
        btn5.text=""
        btn5.setBackgroundColor(R.drawable.transparente)
        btn6.isEnabled=true
        btn6.text=""
        btn6.setBackgroundColor(R.drawable.transparente)
        btn7.isEnabled=true
        btn7.text=""
        btn7.setBackgroundColor(R.drawable.transparente)
        btn8.isEnabled=true
        btn8.text=""
        btn8.setBackgroundColor(R.drawable.transparente)
        btn9.isEnabled=true
        btn9.text=""
        btn9.setBackgroundColor(R.drawable.transparente)
    }
}
