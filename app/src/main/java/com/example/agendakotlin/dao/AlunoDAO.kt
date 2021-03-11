package com.example.agendakotlin.dao

import com.example.agendakotlin.model.Aluno
import java.util.*

class AlunoDAO {

    fun salva(aluno: Aluno) {
        aluno.id = (contadorDeIds)
        alunos.add(aluno)
        atualizaIds()
    }

    private fun atualizaIds() {
        contadorDeIds++
    }

    fun edita(aluno: Aluno) {
        val alunoEncontrado: Aluno? = buscaAlunoPeloId(aluno)
        if (alunoEncontrado != null) {
            val posicaoDoALuno = alunos.indexOf(alunoEncontrado)
            alunos[posicaoDoALuno] = aluno
        }
    }

    private fun buscaAlunoPeloId(aluno: Aluno): Aluno? {
        for (a in alunos) {
            if (a != null) {
                if (a.id == aluno.id) {
                    return a
                }
            }
        }
        return null
    }

    fun todos(): List<Aluno> {
        return ArrayList(alunos)
    }

    fun remove(aluno: Aluno) {
        val alunoDevolvido: Aluno? = buscaAlunoPeloId(aluno)
        if (alunoDevolvido != null) {
            alunos.remove(alunoDevolvido)
        }
    }

    companion object {
        private val alunos: MutableList<Aluno> = ArrayList()
        private var contadorDeIds = 1
    }
}