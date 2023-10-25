package com.example.tarea4

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.view.View
import layout.SQLiteConnector

class ProductController {



    fun writeProducts(db: SQLiteDatabase, name: String, description: String){
        // Crear la base de datos y la tabla

        // Puedes utilizar db para ejecutar consultas y operaciones en la base de datos

        // Agregar registros a la tabla
        val product = Product(1, name, description)


        // Insertar registros
        insertProduct(db, product)

        // Cerrar la conexión con la base de datos
        db.close()
        SQLiteConnector.closeDatabase()
    }

    fun insertProduct(db: SQLiteDatabase, product: Product) {
        val values = ContentValues()
        values.put("name", product.name)
        values.put("description", product.description)
        db.insert("Product", null, values)
    }

    @SuppressLint("Range")
     fun readProducts(db: SQLiteDatabase): List<Product> {
        val products = mutableListOf<Product>()
        val cursor: Cursor?
        try {
            cursor = db.rawQuery("SELECT * FROM Product", null)
        } catch (e: SQLException) {
            db.execSQL("CREATE TABLE Product (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT)")
            return emptyList()
        }

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val description = cursor.getString(cursor.getColumnIndex("description"))
                products.add(Product(id, name, description))
            } while (cursor.moveToNext())
        } else{
            print("El cursor esta vacio")
        }

        cursor.close()
        return products
    }


    fun deleteProduct(db: SQLiteDatabase, productId: Int): Boolean {
        val whereClause = "id = ?"
        val whereArgs = arrayOf(productId.toString())
        val deletedRows = db.delete("Product", whereClause, whereArgs)
        return deletedRows > 0
    }

    fun editProduct(db: SQLiteDatabase, productId: Int, newName: String, newDescription: String): Boolean {
        val values = ContentValues()
        values.put("name", newName)
        values.put("description", newDescription)

        val whereClause = "id = ?"
        val whereArgs = arrayOf(productId.toString())

        val updatedRows = db.update("Product", values, whereClause, whereArgs)

        return updatedRows > 0
    }

}