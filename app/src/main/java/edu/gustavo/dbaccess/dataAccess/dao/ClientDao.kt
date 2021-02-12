package edu.gustavo.dbaccess.dataAccess.dao

import androidx.room.*
import edu.gustavo.dbaccess.dataAccess.Client
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(client: Client)

    @Update
    suspend fun update(client: Client)

    @Delete
    suspend fun delete(client: Client)

    @Query("SELECT * FROM ${Client.TABLE_NAME} ORDER BY ${Client.CLIENT_ID}")
    fun getOrderedClients(): Flow<MutableList<Client>>

    @Query("SELECT * FROM ${Client.TABLE_NAME} WHERE ${Client.CLIENT_ID} = :id")
    fun getClientById(id: Int): Flow<Client>
}