package com.example.tarea4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import layout.SQLiteConnector

class EditProduct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_product)

        SQLiteConnector.initialize(this)

        val id = intent.getIntExtra("id", 0)
        val name = intent.getStringExtra("name")
        val description = intent.getStringExtra("description")

        val editTextName = findViewById<EditText>(R.id.edtName)
        val editTextDescription = findViewById<EditText>(R.id.edtDescription)

        editTextName.setText(name)
        editTextDescription.setText(description)

        // Hacer algo con los datos recibidos
        println(id.toString())
        println(description)
        println(name)
        val btnEdit = findViewById<Button>(R.id.btnEdit)
        btnEdit.setOnClickListener{
            if ((id != 0) && (name != null) && (description != null)) {
                val newName: String = editTextName.text.toString()
                val newDescription: String = editTextDescription.text.toString()
                val db = SQLiteConnector.getWritableDatabase()
                ProductController().editProduct(db, id , newName, newDescription)
                db.close()
                SQLiteConnector.closeDatabase()
                finish()
            }else{
                Toast.makeText(applicationContext, "No se puede editar el id esta perdido", Toast.LENGTH_SHORT).show()
            }
        }
    }
}