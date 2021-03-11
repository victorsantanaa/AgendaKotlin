package com.example.agendakotlin.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.agendakotlin.R
import com.example.agendakotlin.model.Aluno
import java.util.ArrayList

class ListaAlunosAdapter(private var context: Context) : BaseAdapter() {

    private val alunos: MutableList<Aluno> = ArrayList()

    override fun getCount() = alunos.size

    override fun getItem(position: Int): Aluno = alunos[position]

    override fun getItemId(position: Int) : Long = alunos[position].id.toLong()

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
        val viewCriada: View = LayoutInflater
                .from(this.context)
                .inflate(R.layout.item_aluno, viewGroup, false)

        val alunoDevolvido: Aluno = alunos[position]
        vincula(viewCriada, alunoDevolvido)

        return viewCriada
    }

    private fun vincula(view: View, aluno: Aluno) {
        val nome: TextView = view.findViewById(R.id.item_aluno_nome)
        val telefone: TextView = view.findViewById(R.id.item_aluno_telefone)
        nome.text = aluno.nome
        telefone.text = aluno.telefone
    }

    fun atualiza(alunos: List<Aluno>?) {
        this.alunos.clear()
        this.alunos.addAll(alunos!!)
        notifyDataSetChanged()
    }

    fun remove(aluno: Aluno) {
        alunos.remove(aluno)
        notifyDataSetChanged()
    }
}