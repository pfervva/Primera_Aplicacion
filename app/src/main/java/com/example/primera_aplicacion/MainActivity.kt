package com.example.primera_aplicacion

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val botonLlamada = findViewById<ImageButton>(R.id.boton_llamada)

        botonLlamada.setOnClickListener {
            val intent = Intent(this, LlamadaActivity::class.java)
            startActivity(intent)
        }
    }
}