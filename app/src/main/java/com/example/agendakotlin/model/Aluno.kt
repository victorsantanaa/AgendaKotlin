package com.example.agendakotlin.model

import java.io.Serializable

class Aluno : Serializable {

    constructor(nome: String, telefone: String, email: String) {
        this.nome = nome
        this.telefone = telefone
        this.email = email
    }

    constructor()

    lateinit var nome : String
    lateinit var telefone : String
    lateinit var email : String
    var id = 0

    override fun toString() = nome

    fun idIsValid() : Boolean = id > 0

}
