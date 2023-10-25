package com.example.tarea4.adapter

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tarea4.EditProduct
import com.example.tarea4.MainActivity
import com.example.tarea4.Product
import com.example.tarea4.ProductController
import com.example.tarea4.R
import layout.SQLiteConnector

class ProductAdapter(private val data: List<Product>, private val db: SQLiteDatabase) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTV: TextView = itemView.findViewById(R.id.tvItemName)
        val descriptionTV: TextView = itemView.findViewById(R.id.tvItemDescription)
        val edit: Button = itemView.findViewById(R.id.btnEdit)
        val delete: Button = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context
        val description = data[position].description
        val productId = data[position].id
        val name = data[position].name

        holder.nameTV.text = name
        holder.descriptionTV.text = description


        // Configura un OnClickListener para el botón
        holder.delete.setOnClickListener {
            if(ProductController().deleteProduct(db, productId))
                println("Producto eliminado exitosamente.")
            else
                println("No se pudo eliminar el producto")
        }

        holder.edit.setOnClickListener {
            val intent = Intent(context, EditProduct::class.java)
            intent.putExtra("id", productId)
            intent.putExtra("name", name)
            intent.putExtra("description", description)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

