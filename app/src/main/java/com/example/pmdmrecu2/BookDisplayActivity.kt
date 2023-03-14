package com.example.pmdmrecu2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pmdmrecu2.databinding.ActivityBookDisplayBinding
import com.google.gson.Gson


class BookDisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBookDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val compartir = getSharedPreferences("Libro", MODE_PRIVATE)
        val gson = Gson()
        var libroString = compartir.getString("Libro", "")
        val libro = gson.fromJson(libroString, Libro::class.java)

        binding.autorL.text = libro.autor.toString()
        binding.paginasL.text = libro.paginas.toString()
        binding.tituloL.text = libro.titulo.toString()
        binding.yearL.text = libro.year.toString()

        binding.aAdir.setOnClickListener(){
            val editor = compartir.edit()
            libroString = gson.toJson(libro)
            editor.putString("Libro", libroString)
            editor.apply()

            val intent = Intent(this@BookDisplayActivity, MainActivity::class.java)
            startActivity(intent)
        }

        binding.button3.setOnClickListener(){
            val intent = Intent(this@BookDisplayActivity, BookDetailsActivity::class.java)
            startActivity(intent)
        }
    }
}