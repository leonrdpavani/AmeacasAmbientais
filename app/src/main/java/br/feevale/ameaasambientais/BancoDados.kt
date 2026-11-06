package br.feevale.ameaasambientais

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// Cria o banco SQLite e faz as operações na tabela de ameaças
class BancoDados(context: Context) : SQLiteOpenHelper(context, "ameacas.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE ameacas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "endereco TEXT NOT NULL, " +
                "data TEXT NOT NULL, " +
                "descricao TEXT NOT NULL)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ameacas")
        onCreate(db)
    }

    private fun valores(ameaca: Ameaca) = ContentValues().apply {
        put("endereco", ameaca.endereco)
        put("data", ameaca.data)
        put("descricao", ameaca.descricao)
    }

    fun inserir(ameaca: Ameaca): Long = writableDatabase.insert("ameacas", null, valores(ameaca))

    fun atualizar(ameaca: Ameaca): Int =
        writableDatabase.update("ameacas", valores(ameaca), "id = ?", arrayOf(ameaca.id.toString()))

    fun excluir(id: Long): Int = writableDatabase.delete("ameacas", "id = ?", arrayOf(id.toString()))

    fun listar(): List<Ameaca> {
        val lista = mutableListOf<Ameaca>()
        readableDatabase.rawQuery("SELECT id, endereco, data, descricao FROM ameacas ORDER BY id", null).use { c ->
            while (c.moveToNext()) {
                lista.add(Ameaca(c.getLong(0), c.getString(1), c.getString(2), c.getString(3)))
            }
        }
        return lista
    }

    fun buscar(id: Long): Ameaca? {
        readableDatabase.rawQuery(
            "SELECT id, endereco, data, descricao FROM ameacas WHERE id = ?", arrayOf(id.toString())
        ).use { c ->
            return if (c.moveToFirst()) Ameaca(c.getLong(0), c.getString(1), c.getString(2), c.getString(3)) else null
        }
    }
}
