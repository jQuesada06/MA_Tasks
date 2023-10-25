package layout

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.tarea4.DatabaseHelper

object SQLiteConnector {
    private var dbHelper: DatabaseHelper? = null

    fun initialize(context: Context) {
        dbHelper = DatabaseHelper(context)
    }

    fun getWritableDatabase(): SQLiteDatabase {
        return dbHelper!!.writableDatabase
    }

    fun closeDatabase() {
        dbHelper?.close()
    }

}