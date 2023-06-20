package Entidades

data class Game(
    val id: Long,
    val name: String,
    val releaseDate: String,
    val genre: String,
    val price: Double,
    val permalink: String
)