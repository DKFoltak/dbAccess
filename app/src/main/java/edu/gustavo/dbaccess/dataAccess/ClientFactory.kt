package edu.gustavo.dbaccess.dataAccess

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ClientFactory(var application: Application, var client : Client? = null) : ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ClientViewModel(client, Repositorio(application)) as T
    }
}