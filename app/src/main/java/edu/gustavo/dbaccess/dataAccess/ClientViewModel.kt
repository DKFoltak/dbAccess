package edu.gustavo.dbaccess.dataAccess

import androidx.lifecycle.ViewModel

class ClientViewModel(var seleccionado : Client?, var repositorio: Repositorio) : ViewModel() {
    fun upsert() {
        repositorio.upsert(seleccionado!!)
    }
    fun delete() {
        repositorio.delete(seleccionado!!)
    }
}