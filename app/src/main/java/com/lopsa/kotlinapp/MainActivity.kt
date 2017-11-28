package com.lopsa.kotlinapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

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
    }
    fun doStuff(){
        toast("Click Snackbar")
    }
}
