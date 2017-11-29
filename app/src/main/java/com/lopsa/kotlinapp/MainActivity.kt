package com.lopsa.kotlinapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.design.snackbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
           val helper= ConexionNetwordService()
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

    }

    fun doStuff(){
        toast("Click Snackbar")
    }



}
