package Entidades.Intermediarios

enum class TiendaDeCompras {
    STEAM,EPICGAMES,NAKAMA;


    override fun toString(): String {
        return when (this) {

            STEAM -> "Steam"
            EPICGAMES -> "Epic Games"
            NAKAMA -> "Nakama"
        }
    }
}