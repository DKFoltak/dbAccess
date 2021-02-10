package edu.gustavo.dbaccess.dataAccess

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class Repositorio(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        Log.d("Repositorio", "BD creada")
        db!!.execSQL(
            "CREATE TABLE $CLIENT_TABLE (" +
                    "$CLIENT_ID integer primary key autoincrement not null," +
                    "$CLIENT_NAME text not null" +
                    ")"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun list(name : String = ""): MutableList<Client> {
        val list = mutableListOf<Client>()
        val db = this.writableDatabase
        var where : String? = null
        var whereArgs : Array<String>? = null
        if (name != "") {
            where = "$CLIENT_NAME=?"
            whereArgs = arrayOf(name)
        }
        val clientCursor = db.query(
            CLIENT_TABLE, arrayOf(CLIENT_ID, CLIENT_NAME), where, whereArgs, null,
            null, CLIENT_ID
        )
        Log.d("Repositorio", "BD leida")
        if (clientCursor.moveToFirst()) {
            do {
                val client = Client(clientCursor.getLong(0), clientCursor.getString(1))
                Log.d("Repositorio", client.toString())
                list.add(
                    list.count(),
                    client
                )
            } while (clientCursor.moveToNext())
        }
        return list
    }

    fun get(id : Long): Client {
        val db = this.writableDatabase
        val clientCursor =
            db.query(CLIENT_TABLE, arrayOf(CLIENT_ID, CLIENT_NAME), "[$CLIENT_ID]=?", arrayOf(id.toString()), null, null, null)
        if (clientCursor.moveToFirst()) {
            val client = Client(clientCursor.getLong(0), clientCursor.getString(1))
            Log.d("Repositorio", client.toString())
            return client
        }
        return Client()
    }

    fun upsert(client: Client) {
        val content = ContentValues()
        content.put(CLIENT_NAME, client.name)
        val db = this.writableDatabase
        if (get(client.id).id != 0L) {
            Log.d("Repositorio", "actualizado")
            db.update(CLIENT_TABLE, content, "[$CLIENT_ID]=?", arrayOf(client.id.toString()))
        }
        else {
            Log.d("Repositorio", "insertado")
            client.id = db.insert(CLIENT_TABLE, null, content)
        }
    }

    fun delete(client: Client) {
        val db = this.writableDatabase
        Log.d("Repositorio", "borrado")
        db.delete(CLIENT_TABLE, "[$CLIENT_ID]=?", arrayOf(client.id.toString()))
    }

    companion object {
        const val VERSION = 1
        const val DB_NAME = "client_db.sqlite"
        const val CLIENT_TABLE = "client"
        const val CLIENT_ID = "client_id"
        const val CLIENT_NAME = "client_name"
    }
}