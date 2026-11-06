package br.feevale.ameaasambientais

// Representa uma ameaça ambiental (um registro da tabela "ameacas")
data class Ameaca(
    val id: Long = 0,
    val endereco: String,
    val data: String,
    val descricao: String
)
