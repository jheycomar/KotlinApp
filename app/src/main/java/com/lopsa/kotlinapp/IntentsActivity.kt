package com.lopsa.kotlinapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.drm.DrmStore
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_intents.*
import org.jetbrains.anko.*

class IntentsActivity : AppCompatActivity() {
    private val phoneNumber="76405592"
    private val phoneCalCode = 100
    private val pictureFromCamera = 50


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intents)

        //flecha atras
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        btnCamera.setOnClickListener {
            //abrir camara
            val intentCamera = Intent("android.media.action.IMAGE_CAPTURE")
            // startActivity(intentCamera);esto solo habre solo la camara
            startActivityForResult(intentCamera, pictureFromCamera)//abre la camara y espera una captura de imagen
        }


        btnCall.setOnClickListener{
          //  makeCall("76405592")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // comprovar si a haceptado, no a haceptado, o nunca sele a preguntado
                if (checkPermission(Manifest.permission.CALL_PHONE)) {//ha aceptado
                    val intentphone = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber))
                    val  i = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber))
                    if (ActivityCompat.checkSelfPermission(this@IntentsActivity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return@setOnClickListener
                    }
                    startActivity(i)
                } else {//no ha aceptado
                    //a denegado o es la primera vez que se le pregunta
                    if (!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                        //vercion new android 6 or >
                        requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), phoneCalCode)
                    } else {
                        //a denegado
                        longToast( "please enable the request permission")
                        val i = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        i.addCategory(Intent.CATEGORY_DEFAULT)
                        i.data = Uri.parse("package:" + packageName)
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
                        startActivity(i)
                    }

                }

            } else {
                //vercion antigua android
                OlderVersions(phoneNumber)
            }


        }
        btnSms.setOnClickListener{sendSMS("76405592","mensaje")}
        btnWeb.setOnClickListener{browse("http://www.google.com")}
        btnShare.setOnClickListener{share("Mira esto","Asunto")}
        btnEmail.setOnClickListener { email("omardj681@gmail.com","asunto","Mensaje de correo") }



    }

    fun OlderVersions(phonenumber: String) {
        val intentCall = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phonenumber))
        if (checkPermission(Manifest.permission.CALL_PHONE)) {
            startActivity(intentCall)
        } else {
            longToast("you declined the acces")
        }

    }

    // verifica si el usuario a dado permisos ala aplicacion
    private fun checkPermission(permision: String): Boolean {
        val resul = this.checkCallingOrSelfPermission(permision)
        return resul == PackageManager.PERMISSION_GRANTED
    }



    //para generar este metodo escrivir onRequest y tab
    //este metodo controla la respuesta de los permisos que de el ususario
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            phoneCalCode -> {
                val permission = permissions[0]
                val result = grantResults[0]
                if (permission == Manifest.permission.CALL_PHONE) {
                    //comprovar si a sido haceptado
                    if (result == PackageManager.PERMISSION_GRANTED) {

                        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber))
                        val  i = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber))
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                            return
                        }
                        startActivity(i)
                    } else {
                        //no consedio su permiso
                        Toast.makeText(this@IntentsActivity, "you declined the acces", Toast.LENGTH_LONG).show()
                    }

                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

}
