package br.feevale.ameaasambientais

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ajustarBarraNavegacao(findViewById(R.id.raiz))

        findViewById<Button>(R.id.btnNovaAmeaca).setOnClickListener {
            startActivity(Intent(this, AdicionarActivity::class.java))
        }

        // Na Parte 1 a lista fica vazia; o clique em um item abrirá a tela de edição
        findViewById<ListView>(R.id.lvAmeacas).setOnItemClickListener { _, _, _, _ ->
            startActivity(Intent(this, EditarActivity::class.java))
        }
    }
}

// Evita que a barra de navegação do sistema cubra os botões no rodapé
fun ajustarBarraNavegacao(raiz: View) {
    val paddingInferior = raiz.paddingBottom
    ViewCompat.setOnApplyWindowInsetsListener(raiz) { v, insets ->
        val barras = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(v.paddingLeft, v.paddingTop, v.paddingRight, paddingInferior + barras.bottom)
        insets
    }
}
