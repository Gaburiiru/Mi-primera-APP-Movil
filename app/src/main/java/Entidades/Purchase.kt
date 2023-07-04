package Entidades

data class Purchase(
    val id: Long,
    private val userId: String,
    private val gameId: Long,
    private val amount: Double,
    private val createdDate: String,
    private val timeDate: String,
)