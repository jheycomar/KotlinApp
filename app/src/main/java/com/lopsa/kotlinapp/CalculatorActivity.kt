package com.lopsa.kotlinapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_calculator.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.longToast
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

class CalculatorActivity : AppCompatActivity() {
    var operacion="*"
    var numeroPrevio=""
    var nuevaOperacion=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        btnAc.onClick { limpiar() }
        btnMasMenos.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                if (v != null) {
                    operaciones(v)
                }
            }
        })
        btnPorcentage.onClick { porsentage() }
        btnDivicion.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                if (v != null) {
                    operaciones(v)
                }
            }
        })
        btnMultiplicacion.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                if (v != null) {
                    operaciones(v)
                }
            }
        })
        btnMenos.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                if (v != null) {
                    operaciones(v)
                }
            }
        })
        btnMas.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                if (v != null) {
                    operaciones(v)
                }
            }
        })
        btnIgual.onClick { resultado() }

        btnPunto.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                if (v != null) {
                    eventosBtn(v)
                }
            }
        })
        btnCero.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                if (v != null) {
                    eventosBtn(v)
                }
            }
        })
        btnUno.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                if (v != null) {
                    eventosBtn(v)
                }
            }
        })
        btnDos.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                if (v != null) {
                    eventosBtn(v)
                }
            }
        })
        btnTres.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                if (v != null) {
                    eventosBtn(v)
                }
            }
        })
        btnCuatro.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                if (v != null) {
                    eventosBtn(v)
                }
            }
        })
        btnCinco.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                if (v != null) {
                    eventosBtn(v)
                }
            }
        })
        btnSeis.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                if (v != null) {
                    eventosBtn(v)
                }
            }
        })
        btnSiete.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                if (v != null) {
                    eventosBtn(v)
                }
            }
        })
        btnOcho.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                if (v != null) {
                    eventosBtn(v)
                }
            }
        })
        btnNueve.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                if (v != null) {
                    eventosBtn(v)
                }
            }
        })
    }
    fun eventosBtn(view: View){
            if (nuevaOperacion){
                editTextNumeros.setText("")
            }
            nuevaOperacion=false
            val btncliqueado=view as Button
            var valordelBtn:String= editTextNumeros.text.toString()
            when(btncliqueado.id){
                btnCero.id ->{valordelBtn +="0"}
                btnUno.id ->{valordelBtn +="1"}
                btnDos.id ->{valordelBtn +="2"}
                btnTres.id ->{valordelBtn +="3"}
                btnCuatro.id ->{valordelBtn +="4"}
                btnCinco.id ->{valordelBtn +="5"}
                btnSeis.id ->{valordelBtn +="6"}
                btnSiete.id ->{valordelBtn +="7"}
                btnOcho.id ->{valordelBtn +="8"}
                btnNueve.id ->{valordelBtn +="9"}
                btnPunto.id ->{valordelBtn +="."}
                btnMenos.id ->{valordelBtn ="-" +valordelBtn}
            }
            editTextNumeros.setText(valordelBtn)

    }

    fun operaciones(view: View){
        val btncliqueado=view as Button
        when(btncliqueado.id){
            btnDivicion.id ->{
                operacion="/"
            }
            btnMultiplicacion.id->{
                operacion="*"
            }
            btnMenos.id ->{
                operacion="-"
            }
            btnMas.id->{operacion="+"}
        }
        numeroPrevio=editTextNumeros.text.toString()
        nuevaOperacion=true

    }

    fun resultado(){
        var numeroNuevo=editTextNumeros.text.toString()
        if (numeroPrevio == "" ){
           longToast("ingrese un numero")
            return
        }

        var numeroFinal:Double?=null
        when(operacion){
            "*"->numeroFinal=numeroPrevio.toDouble()*numeroNuevo.toDouble()
            "/"->numeroFinal=numeroPrevio.toDouble()/numeroNuevo.toDouble()
            "-"->numeroFinal=numeroPrevio.toDouble()-numeroNuevo.toDouble()
            "+"->numeroFinal=numeroPrevio.toDouble()+numeroNuevo.toDouble()

        }
        editTextNumeros.setText(numeroFinal.toString())
    }

    fun porsentage(){
        val numero:Double=editTextNumeros.text.toString().toDouble()/100
        editTextNumeros.setText(numero.toString())
        nuevaOperacion=true
    }

    fun limpiar(){
        editTextNumeros.setText("0")
        nuevaOperacion=true
    }
}
