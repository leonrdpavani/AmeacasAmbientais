package br.feevale.ameaasambientais

import android.app.Activity
import android.os.Bundle
import android.widget.Button

class AdicionarActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar)
        ajustarBarraNavegacao(findViewById(R.id.raiz))

        // Na Parte 1 apenas volta para a tela inicial (sem gravar no banco)
        findViewById<Button>(R.id.btnAdicionar).setOnClickListener {
            finish()
        }
    }
}
