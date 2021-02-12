package edu.gustavo.dbaccess.dataAccess

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = Client.TABLE_NAME)
data class Client(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = CLIENT_ID)
    @NotNull
    var id: Long = 0,
    @ColumnInfo(name = CLIENT_NAME)
    @NotNull
    var name: String = ""
) : Serializable {
    override fun toString(): String {
        return "Cliente {id: $id, name: $name}"
    }

    companion object {
        const val VERSION = 1
        const val DB_NAME = "client_db.sqlite"
        const val TABLE_NAME = "client"
        const val CLIENT_ID = "client_id"
        const val CLIENT_NAME = "client_name"
    }
}