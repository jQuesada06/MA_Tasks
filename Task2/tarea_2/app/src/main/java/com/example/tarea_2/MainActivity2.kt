package com.example.tarea_2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {

    val historialConsulta: MutableMap<String, Int> = mutableMapOf("capital" to 0, "poblacion" to 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val receivedDictionary = intent.getParcelableArrayListExtra<DictionaryEntry>("dic")

        if (receivedDictionary != null) {

            val dictionaryMap = receivedDictionary.associate { it.key to it.value }

            spinnerExample(dictionaryMap)
        } else {
            // Manejar el caso en el que el diccionario no se haya recibido correctamente
        }
    }

    fun spinnerExample(pais: Map<String?, String?>){

        val spinner: Spinner = findViewById(R.id.spinner)

        val datos = listOf("Capital", "Numero de Habitantes")

        // Crea un ArrayAdapter usando los elementos y el diseño predeterminado para el spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, datos)

        // Especifica el diseño que se usará cuando se desplieguen las opciones
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Une el ArrayAdapter al Spinner
        spinner.adapter = adapter

        // Opcionalmente, puedes configurar un escuchador para detectar la selección del usuario
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val itemSeleccionado = datos[position]

                mostrarDato(pais, itemSeleccionado)

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Se llama cuando no se ha seleccionado nada en el Spinner (opcional)

            }
        }
    }

    fun mostrarDato(pais: Map<String?, String?>, dato: String){
        val nombrePais = pais["name"]
        if (dato == "Capital"){
            incremetarConsulta("capital")
            val capital = pais["capital"]
            Toast.makeText(this@MainActivity2, "La capital de $nombrePais es $capital", Toast.LENGTH_SHORT).show()
        } else{
            incremetarConsulta("poblacion")
            val numHabitantes = pais["poblacion"]
            Toast.makeText(this@MainActivity2, "El numero de habitantes que hay en $nombrePais es de $numHabitantes", Toast.LENGTH_SHORT).show()
        }
    }

    fun incremetarConsulta(tipoConsulta: String){
        val num: Int? = historialConsulta[tipoConsulta]?.plus(1)
        this.historialConsulta[tipoConsulta] = num ?: 1
        Toast.makeText(this@MainActivity2, "${historialConsulta[tipoConsulta]}", Toast.LENGTH_SHORT).show()
    }

    fun aceptar(view: View){
        val entryList = historialConsulta.map { (key, value) ->
            DictionaryIntEntry(key, value)
        }.toList()

        val intent = Intent()
        intent.putParcelableArrayListExtra("dic", ArrayList(entryList))
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    fun cancelar(view: View){
        finish()
    }

}