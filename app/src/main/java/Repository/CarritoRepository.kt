package Repository

import Entidades.Game
import Entidades.Purchase
import Repository.UserRepository.session
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object CarritoRepository {
    val carrito = mutableListOf<Game>()
    val carritoTotal = mutableListOf<Double>()

    fun sumaCarrito(): Double {
        var suma = 0.0
        for (juego in carrito) {
            suma += juego.price
        }
        return suma
    }

    fun agregarCompra() {
        val purchasesList = PurchaseRepositoryProvider.purchasesList
        val id: Long = purchasesList.last().id + 1
        val fechaActual = Calendar.getInstance().time
        val formatoFecha = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val fechaActualString = formatoFecha.format(fechaActual)

        val horaActual = Calendar.getInstance().time
        val formatoHora = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val horaActualString = formatoHora.format(horaActual)

        for (juegoDeCarrito in carrito) {
            val purchase = Purchase(id, session[0].id, juegoDeCarrito.id, juegoDeCarrito.price, fechaActualString, horaActualString)
            purchasesList.add(purchase)
        }
    }
}
