package com.example.tarea4

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tarea4.adapter.ProductAdapter
import layout.SQLiteConnector

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SQLiteConnector.initialize(this)

        initRecyclerView()


        val btnAdd = findViewById<ImageButton>(R.id.btnAdd)
        btnAdd.setOnClickListener{
            val intent = Intent(this, AddProduct::class.java)
            startActivity(intent)
        }

        val btnRefresh = findViewById<Button>(R.id.btnRefresh)
        btnRefresh.setOnClickListener{
            initRecyclerView()
        }
    }

    private fun initRecyclerView(){
        val db = SQLiteConnector.getWritableDatabase()

        val productList = ProductController().readProducts(db)

        recyclerView = findViewById(R.id.recyclerProducts)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ProductAdapter(productList, db)

        recyclerView.adapter = adapter
    }


    fun printProducts() {
        val context: Context = this // Obtén el contexto de tu aplicación

        // Crear la base de datos y la tabla
        val db = SQLiteConnector.getWritableDatabase()

        // Leer e imprimir los registros
        val products = ProductController().readProducts(db)
        for (product in products) {
            println("ID: ${product.id}, Name: ${product.name}, Description: ${product.description}")
        }

        // Cerrar la conexión con la base de datos
        db.close()
        SQLiteConnector.closeDatabase()
    }

}