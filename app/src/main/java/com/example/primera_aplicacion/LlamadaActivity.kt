package com.example.primera_aplicacion
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class LlamadaActivity : AppCompatActivity() {
    private val REQUEST_PHONE_PERMISSION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.llamada)

        val botonLlamada2 = findViewById<ImageButton>(R.id.boton_llamada2)

        botonLlamada2.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), REQUEST_PHONE_PERMISSION)
                } else {
                    realizarLlamada()
                }
            } else {
                realizarLlamada()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_PHONE_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                realizarLlamada()
            } else {
                mostrarMensajeDeError()
            }
        }
    }

    private fun realizarLlamada() {
        val numeroTelefono = "tel:652294366"

        val intentLlamada = Intent(Intent.ACTION_CALL, Uri.parse(numeroTelefono))

        if (intentLlamada.resolveActivity(packageManager) != null) {
            startActivity(intentLlamada)
        } else {
            mostrarMensajeDeError()
        }
    }

    private fun mostrarMensajeDeError() {
        Toast.makeText(this, "No se han concedido los permisos necesarios", Toast.LENGTH_SHORT).show()
    }
}