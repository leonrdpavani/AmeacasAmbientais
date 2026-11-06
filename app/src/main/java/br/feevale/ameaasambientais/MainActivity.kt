package br.feevale.ameaasambientais

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : Activity() {
    private lateinit var banco: BancoDados
    private lateinit var lvAmeacas: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ajustarBarraNavegacao(findViewById(R.id.raiz))

        banco = BancoDados(this)
        lvAmeacas = findViewById(R.id.lvAmeacas)

        findViewById<Button>(R.id.btnNovaAmeaca).setOnClickListener {
            startActivity(Intent(this, AdicionarActivity::class.java))
        }

        // Clique curto: abre a ameaça selecionada para edição
        lvAmeacas.setOnItemClickListener { _, _, _, id ->
            val intent = Intent(this, EditarActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }

        // Clique longo: exclui a ameaça selecionada e continua na tela inicial
        lvAmeacas.setOnItemLongClickListener { _, _, _, id ->
            banco.excluir(id)
            Toast.makeText(this, "Ameaça excluída", Toast.LENGTH_SHORT).show()
            carregarLista()
            true
        }
    }

    // Recarrega a lista sempre que a tela volta a aparecer (após adicionar ou editar)
    override fun onResume() {
        super.onResume()
        carregarLista()
    }

    private fun carregarLista() {
        lvAmeacas.adapter = AmeacaAdapter(this, banco.listar())
    }
}

// No Android 15+ o app ocupa a tela inteira: evita que a barra de status, a barra do título,
// a barra de navegação e o teclado cubram o conteúdo
fun ajustarBarraNavegacao(raiz: View) {
    val paddingSuperior = raiz.paddingTop
    val paddingInferior = raiz.paddingBottom
    ViewCompat.setOnApplyWindowInsetsListener(raiz) { v, insets ->
        val barras = insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime())
        v.setPadding(v.paddingLeft, paddingSuperior + barras.top, v.paddingRight, paddingInferior + barras.bottom)
        insets
    }
}
