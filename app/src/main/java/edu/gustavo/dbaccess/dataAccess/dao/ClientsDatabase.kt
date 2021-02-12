package edu.gustavo.dbaccess.dataAccess.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import edu.gustavo.dbaccess.dataAccess.Client

@Database(entities = arrayOf(Client::class), version = Client.VERSION, exportSchema = false)
abstract class ClientsDatabase : RoomDatabase() {
    abstract fun dao(): ClientDao

    companion object {
        @Volatile
        private var INSTANCE: ClientsDatabase? = null

        fun getInstance(context: Context): ClientsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ClientsDatabase::class.java,
                    Client.DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}