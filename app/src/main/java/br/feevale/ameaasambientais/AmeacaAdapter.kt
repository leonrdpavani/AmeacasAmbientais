package br.feevale.ameaasambientais

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

// Adaptador que mostra cada ameaça do banco como uma linha do ListView
class AmeacaAdapter(private val context: Context, private val ameacas: List<Ameaca>) : BaseAdapter() {

    override fun getCount(): Int = ameacas.size

    override fun getItem(position: Int): Ameaca = ameacas[position]

    override fun getItemId(position: Int): Long = ameacas[position].id

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_ameaca, parent, false)
        val ameaca = ameacas[position]
        view.findViewById<TextView>(R.id.txtDescricao).text = ameaca.descricao
        view.findViewById<TextView>(R.id.txtData).text = ameaca.data
        return view
    }
}
