package edu.gustavo.dbaccess.dataAccess.dao

import android.app.Application
import androidx.annotation.WorkerThread
import edu.gustavo.dbaccess.dataAccess.Client
import kotlinx.coroutines.flow.Flow

class RepositorioDao(application: Application) {
    private val clientDao: ClientDao = ClientsDatabase.getInstance(application)?.dao()

    @WorkerThread
    suspend fun upsert(client : Client) {
        if (client.id != 0L)
            clientDao.update(client)
        else
            clientDao.insert(client)
    }

    @WorkerThread
    suspend fun delete(client : Client) {
        clientDao.delete(client)
    }

    fun findById(id: Int): Flow<Client> {
        return clientDao.getClientById(id)
    }

    val list : Flow<MutableList<Client>> = clientDao.getOrderedClients()

}