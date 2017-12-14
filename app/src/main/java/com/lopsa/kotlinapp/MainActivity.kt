package com.lopsa.kotlinapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.lopsa.kotlinapp.R.string.intent
import com.lopsa.kotlinapp.services.INetwordConneccion
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //forsar el icono en el actionbar
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        val icon=  supportActionBar!!.setIcon(R.mipmap.ic_kotlin)

        //manejador de eventos click
        fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.mipmap.ic_kotlin -> {
                    //añadimos nuevo nombre
                 toast("click in icon")
                    return true
                }

                else -> return super.onOptionsItemSelected(item)
            }

        }

        btnIntent.setOnClickListener {
            startActivity(intentFor<IntentsActivity>("string" to "Intend Activity"))
        }
        btnDialog.setOnClickListener { startActivity( intentFor<DialogActivity>())}

        btnfab.setOnClickListener (object :View.OnClickListener{
            override fun onClick(v: View) {
                snackbar(v, "Action, reaction", "Click me!") { doStuff() }
            }
        })

        btntaskAsync.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
               startActivity(intentFor<TasksinthebackgroundActivity>())
            }
        })

        btnIntentcomprovate.setOnClickListener{
           val helper= INetwordConneccion()
           var conexion =helper.compruebaConexion(this)
            if (conexion==true){
                alert("Conexion Exitosa","USTED TIENE CONEXION A INTERNET") {
                    yesButton {longToast("acepto su conexion")  }
                    noButton {longToast("cancelo su conexion")  }
                }.show()

            }else{
              longToast("Berifique su conexion a Internet")
            }
        }

        btnPlace!!.onClick { startActivity(intentFor<PlacesActivity>())}

        btnCalculator.onClick { startActivity(intentFor<CalculatorActivity>()) }

    }


    fun doStuff(){
        toast("Click Snackbar")
    }



}
