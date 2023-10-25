package com.example.tarea4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import layout.SQLiteConnector

class AddProduct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        SQLiteConnector.initialize(this)

        val editTextName = findViewById<EditText>(R.id.edtName)


        val editTextDescription = findViewById<EditText>(R.id.edtDescription)



        val btnAdd = findViewById<Button>(R.id.btnAdd)
        btnAdd.setOnClickListener {
            val name: String = editTextName.text.toString()
            val description: String = editTextDescription.text.toString()
            println("Nombre: $name Descripcion: $description")
            val db = SQLiteConnector.getWritableDatabase()
            ProductController().writeProducts(db, name, description)
            finish()
        }


        val btnCancel = findViewById<Button>(R.id.btnCancel)
        btnCancel.setOnClickListener {
            finish()
        }
    }



}