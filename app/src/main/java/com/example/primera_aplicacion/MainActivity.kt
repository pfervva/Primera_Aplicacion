package com.example.primera_aplicacion

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonLlamada = findViewById<ImageButton>(R.id.boton_llamada)
        val botonWeb = findViewById<ImageButton>(R.id.web)

        botonLlamada.setOnClickListener {
            val llamadaIntent = Intent(this, LlamadaActivity::class.java)
            startActivity(llamadaIntent)
        }

        botonWeb.setOnClickListener {
            val url = "https://www.google.com"
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(webIntent)
        }
    }
}




