package com.example.agendakotlin

import android.app.Application
import com.example.agendakotlin.dao.AlunoDAO
import com.example.agendakotlin.model.Aluno

class AgendaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        criaAlunosDeTeste()
    }

    private fun criaAlunosDeTeste() {
        val dao = AlunoDAO()
        for (i in 0..14) {
            dao.salva(Aluno("Victor", "11985402050", "victor.santanaribeiro@gamil.com"))
            dao.salva(Aluno("Jamal", "11985402050", "victor.santanaribeiro@gamil.com"))
        }
    }
}