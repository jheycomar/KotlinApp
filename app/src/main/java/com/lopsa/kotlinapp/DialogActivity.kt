package com.lopsa.kotlinapp

import android.app.Dialog
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_dialog.*
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.Appcompat
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.design.snackbar

class DialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        btnToas.setOnClickListener {
            toast("Hi there!")
            longToast("Wow, such duration")
        }

        btnSnackBar!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
               // snackbar(view, "Hi there!")
                //longSnackbar(view, "Wow, such duration")
                snackbar(view, "Action, reaction", "Click me!") { doStuff() }

            }
        })
        btnAlert.setOnClickListener { //alert(Appcompat, "Some text message").show()

            alert("Hi, I'm Roy", "¿Has intentado apagarlo y encenderlo de nuevo?") {
                yesButton { toast("yesButton…") }
                noButton {toast("noButton…")}
            }.show()

            //segundo alert
          /*  alert(message="Mensaje",title = "Alert") {
                positiveButton("ok") { toast("clicket ok") }
                negativeButton("cancelar"){toast("cancelar")}
            }.show()*/

            //tercer alert
              /*  alert(message="Mensaje",title = "Alert") {
                    positiveButton("ok") { toast("clicket ok") }
                    customView {
                        verticalLayout {
                             editText("")
                             editText("")
                        }
                    }
                }.show()*/

        }

        btnSelector.setOnClickListener {
            val countries = listOf("Russia", "USA", "Japan", "Australia","Bolivia")
            selector("Where are you from?", countries, { dialogInterface, i ->
                toast("So you're living in ${countries[i]}, right?")
            })
        }


        btnProgressDialog.setOnClickListener {
          val   dialog = progressDialog(message = "Please wait a bit…", title = "Title")
        }
        btnProgressDialog2.setOnClickListener {
           val dialog=indeterminateProgressDialog ("Cargando...","Title")
        }

    }

    fun doStuff(){
        toast("snackbar")
    }
}


