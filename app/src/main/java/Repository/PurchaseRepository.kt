package Repository

import Entidades.Purchase
import Repository.GameRepository.games
import Repository.UserRepository.session

object PurchaseRepository {
    val purchases = mutableListOf<Purchase>()

    init {
        purchases.add(Purchase(1L, "1504L", 1L, 350.50, "2023-01-01","20:30"))
        purchases.add(Purchase(2L, "1504L", 4L, 100.75, "2023-01-01","20:51"))
        purchases.add(Purchase(3L, "1504L", 7L, 250.50, "2023-01-01","01:12"))
        purchases.add(Purchase(4L, "1504L", 10L, 50.00, "2023-01-01","09:52"))
        purchases.add(Purchase(5L, "1504L", 13L, 1350.15, "2023-01-01","10:32"))
        purchases.add(Purchase(6L, "2802L", 2L, 20.30, "2023-01-01","16:29"))
        purchases.add(Purchase(7L, "2802L", 9L, 450.75, "2023-01-01","15:56"))
        purchases.add(Purchase(8L, "2802L", 11L, 500.00, "2023-01-01","18:55"))
        purchases.add(Purchase(9L, "1510L", 3L, 350.50, "2023-01-01","11:20"))
        purchases.add(Purchase(10L, "1510L", 5L, 150.00, "2023-01-01","22:30"))

    }

    fun getBiblioteca() {
        for (purch in purchases){
            if (session[0].id == purch.userId){
                for(game in games){
                    if (purch.gameId == game.id){
                        println("Nombre de juego: ${game.name}, precio de juego ${purch.amount}, fecha de compra: ${purch.createdDate} y hora de compra: ${purch.timeDate}")
                    }
                }
            }
        }
    }
}
