package com.example.pmdmrecu2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pmdmrecu2.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val libro = Libro()


        binding.continuar.setOnClickListener(){
            if(binding.titulo.text.isEmpty() || binding.paginas.text.isEmpty()){
                Toast.makeText(this, "Faltan campos por rellenar", Toast.LENGTH_SHORT).show()
            }else if(binding.paginas.text.toString().toInt() < 0 ){
                Toast.makeText(this, "El número de páginas no puede ser menor de 0", Toast.LENGTH_SHORT).show()
            }else{
                libro.titulo = binding.titulo.text.toString()
                libro.paginas = binding.paginas.text.toString().toInt()

                val compartir = getSharedPreferences("Libro", MODE_PRIVATE)
                val editor = compartir.edit()
                val gson = Gson()
                val libroString = gson.toJson(libro)
                editor.putString("Libro", libroString)
                editor.apply()

                val intent = Intent(this@MainActivity, BookDetailsActivity::class.java)
                startActivity(intent)
            }
        }

    }
}