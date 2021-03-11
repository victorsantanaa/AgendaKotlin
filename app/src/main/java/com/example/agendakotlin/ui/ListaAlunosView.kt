package com.example.agendakotlin.ui

import android.app.AlertDialog
import android.content.Context
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ListView
import com.example.agendakotlin.dao.AlunoDAO
import com.example.agendakotlin.model.Aluno
import com.example.agendakotlin.ui.adapter.ListaAlunosAdapter

class ListaAlunosView() {

    private lateinit var adapter : ListaAlunosAdapter
    private lateinit var context : Context
    private lateinit var dao: AlunoDAO

    constructor(context: Context) : this() {
        this.context = context
        this.adapter = ListaAlunosAdapter(this.context)
        this.dao = AlunoDAO()
    }

    fun confirmaRemocao(item: MenuItem) {

        AlertDialog.Builder(context)
                .setTitle("Removendo aluno")
                .setMessage("Tem certeza que quer remover o aluno?")
                .setPositiveButton("Sim") { _, _ ->
                    val menuInfo: AdapterView.AdapterContextMenuInfo =
                            item.menuInfo as AdapterView.AdapterContextMenuInfo
                    val alunoEscolhido: Aluno = adapter.getItem(menuInfo.position)
                    remove(alunoEscolhido)
                }
                .setNegativeButton("Não", null)
                .show()
    }

    fun atualizaAlunos() {
        adapter.atualiza(dao.todos())
    }

    private fun remove(aluno: Aluno) {
        dao.remove(aluno)
        adapter.remove(aluno)
    }

    fun configuraAdapter(listaDeAlunos: ListView) {
        listaDeAlunos.adapter = adapter
    }

}
