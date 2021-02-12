package edu.gustavo.dbaccess.dataAccess

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import edu.gustavo.dbaccess.dataAccess.dao.RepositorioDao
import kotlinx.coroutines.launch

class ClientViewModel(var application: Application, var seleccionado : Client? = null) : ViewModel() {
    val repositorio by lazy { Repositorio(application) }
    val repositorioDao by lazy { RepositorioDao(application) }

    fun list() : MutableList<Client> {
        return repositorio.list()
    }

    fun upsert() {
        repositorio.upsert(seleccionado!!)
    }

    fun delete() {
        repositorio.delete(seleccionado!!)
    }

    fun listDao() : LiveData<MutableList<Client>> {
        return repositorioDao.list.asLiveData()
    }

    fun upsertDao() = viewModelScope.launch {
        repositorioDao.upsert(seleccionado!!)
    }

    fun deleteDao() = viewModelScope.launch {
        repositorioDao.delete(seleccionado!!)
    }
}