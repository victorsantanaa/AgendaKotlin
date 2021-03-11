package com.example.agendakotlin.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.agendakotlin.R
import com.example.agendakotlin.dao.AlunoDAO
import com.example.agendakotlin.model.Aluno

class FormularioAlunoActivity: AppCompatActivity() {

    private lateinit var campoNome: EditText
    private lateinit var campoTelefone: EditText
    private lateinit var campoEmail: EditText
    private val dao: AlunoDAO = AlunoDAO()
    private lateinit var aluno: Aluno
    private val constantesActivities: ConstantesActivities = ConstantesActivities()

    companion object {
        private const val TITULO_APPBAR_NOVO_ALUNO = "Novo aluno"
        private const val TITULO_APPBAR_EDITA_ALUNO = "Edita aluno"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_aluno)
        inicializacaoDosCampos()
        carregaAluno()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_formulario_alunos_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemId = item.itemId
        if ( itemId == R.id.activity_formulario_aluno_botao_salvar) {
            finalizaFormulario()
        }
        return super.onOptionsItemSelected(item)
    }

    fun carregaAluno() {
        var dados: Intent = intent
        if( dados.hasExtra(constantesActivities.CHAVE_ALUNO)) {
            title = TITULO_APPBAR_EDITA_ALUNO
            aluno = dados.getSerializableExtra(constantesActivities.CHAVE_ALUNO) as Aluno
            preencheCampos()
        } else {
            title = TITULO_APPBAR_NOVO_ALUNO
            aluno = Aluno()
        }
    }

    private fun preencheCampos() {
        campoNome.setText(aluno.nome)
        campoTelefone.setText(aluno.telefone)
        campoEmail.setText(aluno.email)
    }

    private fun finalizaFormulario() {
        preencheAluno()
        if(aluno.idIsValid()) {
            dao.edita(aluno)
        } else {
            dao.salva(aluno)
        }
        finish()
    }

    private fun inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome)
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone)
        campoEmail = findViewById(R.id.activity_formulario_aluno_email)
    }

    private fun preencheAluno() {
        var nome = campoNome.text.toString()
        var telefone = campoTelefone.text.toString()
        var email = campoEmail.text.toString()
        aluno.nome = nome
        aluno.telefone = telefone
        aluno.email = email
    }
}
