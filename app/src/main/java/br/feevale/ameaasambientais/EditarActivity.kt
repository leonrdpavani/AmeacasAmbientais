package br.feevale.ameaasambientais

import android.app.Activity
import android.os.Bundle
import android.widget.Button

class EditarActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar)
        ajustarBarraNavegacao(findViewById(R.id.raiz))

        // Na Parte 1 apenas volta para a tela inicial (sem atualizar o banco)
        findViewById<Button>(R.id.btnAtualizar).setOnClickListener {
            finish()
        }
    }
}
