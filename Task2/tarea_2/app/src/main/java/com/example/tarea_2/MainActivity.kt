package com.example.tarea_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    val paises = listOf(
        mapOf("name" to "Costa Rica", "capital" to "San José", "poblacion" to "5 000 000"),
        mapOf("name" to "Belice", "capital" to "Belmopán", "poblacion" to "400 000"),
        mapOf("name" to "El Salvador", "capital" to "San Salvador", "poblacion" to "6 400 000"),
        mapOf("name" to "Guatemala", "capital" to "Ciudad de Guatemala", "poblacion" to "17 700 000"),
    )

    private lateinit var startForResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listViewAction()

        startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                val resultado = data?.getParcelableArrayListExtra<DictionaryIntEntry>("dic")
                // Hacer algo con el resultado recibido de Activity3
                if (resultado != null) {

                    val historial = resultado.associate { it.key to it.value }

                    Toast.makeText(this@MainActivity, "La capital se consulto ${historial["capital"]} veces y la poblacion ${historial["poblacion"]}", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, "El usuario cancelo la actividad", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun listViewAction(){
        val listView: ListView = findViewById(R.id.lv_paises)


        // Crea un ArrayAdapter para mostrar los nombres en el ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, paises.map {it["name"]})

        // Asocia el ArrayAdapter con el ListView
        listView.adapter = adapter

        // Configura un escuchador para el clic en los elementos del ListView
        listView.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val paisSeleccionado = paises[position]
                callActivity2(paisSeleccionado)
            }
        }
    }

    fun callActivity2(pais: Map<String, String>){

        val entryList = pais.map { (key, value) ->
            DictionaryEntry(key, value)
        }.toList()

        // Crear un Intent para iniciar la Activity2
        val intent = Intent(this, MainActivity2::class.java)

        // Opcional: Puedes enviar datos extras a la Activity2 utilizando putExtra
        intent.putParcelableArrayListExtra("dic", ArrayList(entryList))

        // Iniciar la Activity2 utilizando el Intent
        startActivity(intent)
    }
}