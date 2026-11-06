package br.feevale.ameaasambientais

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AdicionarActivity : Activity() {
    // Seta de voltar da barra do topo: volta para a tela inicial sem salvar
    override fun onNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar)
        ajustarBarraNavegacao(findViewById(R.id.raiz))
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val edtEndereco = findViewById<EditText>(R.id.edtEndereco)
        val edtData = findViewById<EditText>(R.id.edtData)
        val edtDescricao = findViewById<EditText>(R.id.edtDescricao)

        findViewById<Button>(R.id.btnAdicionar).setOnClickListener {
            val endereco = edtEndereco.text.toString().trim()
            val data = edtData.text.toString().trim()
            val descricao = edtDescricao.text.toString().trim()

            if (endereco.isEmpty() || data.isEmpty() || descricao.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            BancoDados(this).inserir(Ameaca(endereco = endereco, data = data, descricao = descricao))
            Toast.makeText(this, "Ameaça adicionada", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
