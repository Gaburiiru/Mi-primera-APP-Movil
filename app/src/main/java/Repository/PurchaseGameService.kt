package Repository

import Entidades.Intermediarios.EpicGames
import Entidades.Intermediarios.Nakama
import Entidades.Intermediarios.Steam
import Entidades.Purchase
import Entidades.User
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Calendar


class PurchaseGameService {
    fun compraSteam(user: User, costoGame :Double) {
        println("Usted tiene disponible $${user.money}")
        println("costo del juego: $$costoGame")
        val comision = costoGame.times(Steam.calcularComision())
        val total = user.money.minus(costoGame + comision)
        user.restarMoney(total)
        val formato = DecimalFormat("#.##")
        val comisionFormateada = formato.format(comision).replace(',', '.')
        println("valor de la comison $comisionFormateada")

        val id:Long = PurchaseRepository.purchases.last().id +1
        val userID = user.id
        val gameId = GameRepository.idGame
        val amount = costoGame
        val calendar = Calendar.getInstance()
        val fecha = calendar.get(Calendar.YEAR).toString() + "-" +
                (calendar.get(Calendar.MONTH) + 1).toString().padStart(2, '0') + "-" +
                calendar.get(Calendar.DAY_OF_MONTH).toString().padStart(2, '0')

        val hora = calendar.get(Calendar.HOUR_OF_DAY).toString().padStart(2, '0') + ":" +
                calendar.get(Calendar.MINUTE).toString().padStart(2, '0')

        PurchaseRepository.purchases.add(Purchase(id, userID, gameId, amount, fecha, hora))

        cashback(costoGame,comisionFormateada.toDouble(),fecha,user)
    }

    fun compraEpicGames(user: User, costoGame :Double) {
        println("Usted tiene disponible $${user.money}")
        println("costo del juego: $$costoGame")
        val comision = costoGame.times(EpicGames.calcularComision())
        val total = user.money.minus(costoGame + comision)
        user.restarMoney(total)
        val formato = DecimalFormat("#,##")
        val comisionFormateada = formato.format(comision).replace(',', '.')
        println("valor de la comison $comisionFormateada")

        val id:Long = PurchaseRepository.purchases.last().id +1
        val userID = user.id
        val gameId = GameRepository.idGame
        val amount = costoGame
        val calendar = Calendar.getInstance()
        val fecha = calendar.get(Calendar.YEAR).toString() + "-" +
                (calendar.get(Calendar.MONTH) + 1).toString().padStart(2, '0') + "-" +
                calendar.get(Calendar.DAY_OF_MONTH).toString().padStart(2, '0')

        val hora = calendar.get(Calendar.HOUR_OF_DAY).toString().padStart(2, '0') + ":" +
                calendar.get(Calendar.MINUTE).toString().padStart(2, '0')

        PurchaseRepository.purchases.add(Purchase(id, userID, gameId, amount, fecha, hora))

        cashback(costoGame,comisionFormateada.toDouble(),fecha,user)
    }

    fun compraNakama(user: User, costoGame :Double) {
        println("Usted tiene disponible $${user.money}")
        println("costo del juego: $${costoGame}")
        val comision = costoGame.times(Nakama.calcularComision())
        val total = user.money.minus(costoGame + comision)
        user.restarMoney(total)
        val formato = DecimalFormat("#,##")
        val comisionFormateada = formato.format(comision).replace(',', '.')
        println("valor de la comison $comisionFormateada")

        val id:Long = PurchaseRepository.purchases.last().id +1
        val userID = user.id
        val gameId = GameRepository.idGame
        val amount = costoGame
        val calendar = Calendar.getInstance()
        val fecha = calendar.get(Calendar.YEAR).toString() + "-" +
                (calendar.get(Calendar.MONTH) + 1).toString().padStart(2, '0') + "-" +
                calendar.get(Calendar.DAY_OF_MONTH).toString().padStart(2, '0')

        val hora = calendar.get(Calendar.HOUR_OF_DAY).toString().padStart(2, '0') + ":" +
                calendar.get(Calendar.MINUTE).toString().padStart(2, '0')

        PurchaseRepository.purchases.add(Purchase(id, userID, gameId, amount, fecha, hora))

        cashback(costoGame,comisionFormateada.toDouble(),fecha,user)
    }
    private fun cashback(costoGame:Double, comision:Double, fechaCompra:String, user: User) {
        val compra = costoGame.plus(comision)
        val fecha = fechaCompra
        val antiguedad = user.createdDate
        val format = java.text.SimpleDateFormat("yyyy-MM-dd")
        val calendarAntigua = Calendar.getInstance().apply {
            time = format.parse(antiguedad)
        }
        val calendarActual = Calendar.getInstance().apply {
            time = format.parse(fecha)
        }
        val diferenciaMeses = (calendarActual.get(Calendar.YEAR) - calendarAntigua.get(Calendar.YEAR)) * 12 +
                calendarActual.get(Calendar.MONTH) - calendarAntigua.get(Calendar.MONTH)
        val MesesRegistrados = diferenciaMeses // número de meses que el usuario ha estado registrado
        val CompraTotal = compra// cantidad total de la compra
        var reintegro:Double
        when(MesesRegistrados) {
            in 0..3 -> {
                reintegro = CompraTotal * 0.05
            } // cashback del 5% si tiene 3 meses o menos de registro
            in 4..12 -> {
                reintegro = CompraTotal * 0.03
            } // cashback del 3% si tiene más de 3 meses, pero 12 meses o menos de registro
            else -> {
                reintegro = 0.0
                println("no se aplicaron descuentos")
            } // no se da cashback si tiene más de 12 meses de registro
        }
        val formatoD = DecimalFormat("#,##")
        val reintegroFormateado = formatoD.format(reintegro).replace(',', '.')
        user.reintegroMoney(reintegroFormateado.toDouble())
        if (reintegro != user.money){
            println("descuento aplicado $${reintegroFormateado.toDouble()}, tu saldo actual es de $${user.money}")
        }
    }
}
