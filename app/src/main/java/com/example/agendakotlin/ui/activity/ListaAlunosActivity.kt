package com.example.agendakotlin.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.agendakotlin.R
import com.example.agendakotlin.model.Aluno
import com.example.agendakotlin.ui.ListaAlunosView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaAlunosActivity : AppCompatActivity() {

    companion object {
        const val TITULO_APPBAR = "Lista de alunos - Kotlin"
    }

    private val listaAlunosView: ListaAlunosView = ListaAlunosView(this)
    private val constantesActivities: ConstantesActivities = ConstantesActivities()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "Victor Santana", Toast.LENGTH_LONG).show()
        setContentView(R.layout.activity_lista_alunos)
        title = TITULO_APPBAR
        configuraFabNovoAluno()
        configuraLista()
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.activity_lista_alunos_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var itemId = item.itemId
        if( itemId == R.id.activity_lista_alunos_menu_remover) {
            listaAlunosView.confirmaRemocao(item)
        }
        return super.onContextItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        listaAlunosView.atualizaAlunos()
    }

    private fun configuraFabNovoAluno() {
        val fab:FloatingActionButton = findViewById(R.id.activity_main_fab_novo_aluno)
        fab.setOnClickListener { abreFormularioInsereAluno() }
    }

    private fun configuraLista() {
        val listaDeAlunos: ListView = findViewById(R.id.activity_main_lista_de_alunos)
        listaAlunosView.configuraAdapter(listaDeAlunos)
        configuraListenerDeCliquePorItem(listaDeAlunos)
        registerForContextMenu(listaDeAlunos)
    }

    private fun configuraListenerDeCliquePorItem(listaDeAlunos: ListView) {
        listaDeAlunos.setOnItemClickListener { parent, view, position, id ->
            var alunoEscolhido: Aluno = parent.getItemAtPosition(position) as Aluno
            configuraListenerDeClique(alunoEscolhido)
        }
    }

    private fun configuraListenerDeClique(alunoEscolhido: Aluno) {
        val intent = Intent(this@ListaAlunosActivity, FormularioAlunoActivity::class.java)
                .apply {
            putExtra(constantesActivities.CHAVE_ALUNO, alunoEscolhido)
        }
            //intent.putExtra(CHAVE_ALUNO, alunoEscolhido)
            startActivity(intent)

    }

    fun abreFormularioInsereAluno() {
        startActivity(Intent(this, FormularioAlunoActivity::class.java))
    }


}