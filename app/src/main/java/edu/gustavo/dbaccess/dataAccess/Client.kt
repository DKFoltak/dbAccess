package edu.gustavo.dbaccess.dataAccess

import java.io.Serializable

class Client (var id: Long = 0, var name: String = "") : Serializable {
    override fun toString(): String {
        return "Cliente {id: $id, name: $name}"
    }
}

