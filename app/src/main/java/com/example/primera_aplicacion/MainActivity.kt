package com.example.primera_aplicacion

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonLlamada = findViewById<ImageButton>(R.id.boton_llamada)
        val botonWeb = findViewById<ImageButton>(R.id.web)
        val botonAlarma = findViewById<ImageButton>(R.id.alarma)
        val botonCaptura = findViewById<ImageButton>(R.id.captura)
        imageView = findViewById(R.id.imageView)

        botonLlamada.setOnClickListener {
            val llamadaIntent = Intent(this, LlamadaActivity::class.java)
            startActivity(llamadaIntent)
        }

        botonWeb.setOnClickListener {
            val url = "https://www.google.com"
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(webIntent)
        }

        botonAlarma.setOnClickListener {
            val currentTime = android.icu.util.Calendar.getInstance()
            currentTime.add(android.icu.util.Calendar.MINUTE, 2)

            val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "Alarma")
                putExtra(AlarmClock.EXTRA_HOUR, currentTime.get(android.icu.util.Calendar.HOUR_OF_DAY))
                putExtra(AlarmClock.EXTRA_MINUTES, currentTime.get(android.icu.util.Calendar.MINUTE))
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
        botonCaptura.setOnClickListener {
            val captura = captureScreen()
            if (captura != null) {
                imageView.setImageBitmap(captura)
                Toast.makeText(this, "Captura pantalla hecha", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "No he podido tomar la captura de pantalla", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun captureScreen(): Bitmap? {
        val vista = window.decorView.rootView
        vista.isDrawingCacheEnabled = true
        val captura = Bitmap.createBitmap(vista.drawingCache)
        vista.isDrawingCacheEnabled = false
        return captura
    }
}
