package Repository

import Entidades.Game

object CarritoRepository {
    val carrito = mutableListOf<Game>()

    init {
        carrito.add(Game(13L, "God of War: Ragnarok", "2022", "Aventura", 5350.00, "https://assets-prd.ignimgs.com/2022/07/25/9781506733494-1658716557072.jpg"))
    }
}