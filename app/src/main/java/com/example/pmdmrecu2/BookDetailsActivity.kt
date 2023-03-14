package com.example.pmdmrecu2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pmdmrecu2.databinding.ActivityBookDetailsBinding
import com.google.gson.Gson


class BookDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBookDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val compartir = getSharedPreferences("Libro", MODE_PRIVATE)
        val gson = Gson()
        var libroString = compartir.getString("Libro", "")
        val libro = gson.fromJson(libroString, Libro::class.java)


        binding.continuar.setOnClickListener(){
            if(binding.autor.text.isEmpty() || binding.publicacion.text.isEmpty()){
                Toast.makeText(this, "Faltan campos por rellenar", Toast.LENGTH_SHORT).show()
            }else if(binding.publicacion.text.toString().toInt() < 0 ){
                Toast.makeText(this, "El año de publicación no puede ser menor de 0", Toast.LENGTH_SHORT).show()
            }else{

                libro.autor = binding.autor.text.toString()
                libro.year = binding.publicacion.text.toString().toInt()

                val editor = compartir.edit()
                libroString = gson.toJson(libro)
                editor.putString("Libro", libroString)
                editor.apply()

                val intent = Intent(this@BookDetailsActivity, BookDisplayActivity::class.java)
                startActivity(intent)
            }
        }

        binding.button.setOnClickListener(){
            val intent = Intent(this@BookDetailsActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }


}