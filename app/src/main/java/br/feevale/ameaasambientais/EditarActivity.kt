package br.feevale.ameaasambientais

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class EditarActivity : Activity() {
    // Seta de voltar da barra do topo: volta para a tela inicial sem salvar
    override fun onNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar)
        ajustarBarraNavegacao(findViewById(R.id.raiz))
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val banco = BancoDados(this)
        val ameaca = banco.buscar(intent.getLongExtra("id", -1))
        if (ameaca == null) {
            finish()
            return
        }

        val edtEndereco = findViewById<EditText>(R.id.edtEndereco)
        val edtData = findViewById<EditText>(R.id.edtData)
        val edtDescricao = findViewById<EditText>(R.id.edtDescricao)

        edtEndereco.setText(ameaca.endereco)
        edtData.setText(ameaca.data)
        edtDescricao.setText(ameaca.descricao)

        findViewById<Button>(R.id.btnAtualizar).setOnClickListener {
            val endereco = edtEndereco.text.toString().trim()
            val data = edtData.text.toString().trim()
            val descricao = edtDescricao.text.toString().trim()

            if (endereco.isEmpty() || data.isEmpty() || descricao.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            banco.atualizar(ameaca.copy(endereco = endereco, data = data, descricao = descricao))
            Toast.makeText(this, "Ameaça atualizada", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
