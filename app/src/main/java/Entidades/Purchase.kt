package Entidades

data class Purchase(
    val id: Long,
    val userId: String,
    val gameId: Long,
    val amount: Double,
    val createdDate: String,
    val timeDate: String,
)