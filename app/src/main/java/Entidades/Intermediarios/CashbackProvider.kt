import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object CashbackProvider {
    fun cashback(subTotal: Double, createdDate: String): Double {
        val formatoFecha = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val fechaActual = Calendar.getInstance().time
        val fechaPasadaDate = formatoFecha.parse(createdDate)

        val calendarActual = Calendar.getInstance()
        calendarActual.time = fechaActual
        calendarActual.set(Calendar.HOUR_OF_DAY, 0)
        calendarActual.set(Calendar.MINUTE, 0)
        calendarActual.set(Calendar.SECOND, 0)
        calendarActual.set(Calendar.MILLISECOND, 0)

        val calendarPasada = Calendar.getInstance()
        calendarPasada.time = fechaPasadaDate
        calendarPasada.set(Calendar.HOUR_OF_DAY, 0)
        calendarPasada.set(Calendar.MINUTE, 0)
        calendarPasada.set(Calendar.SECOND, 0)
        calendarPasada.set(Calendar.MILLISECOND, 0)

        val diffYears = calendarActual.get(Calendar.YEAR) - calendarPasada.get(Calendar.YEAR)
        val diffMonths = calendarActual.get(Calendar.MONTH) - calendarPasada.get(Calendar.MONTH)
        val mesesRegistrados = diffYears * 12 + diffMonths

        return when (mesesRegistrados) {
            in 0..3 -> subTotal * 0.05 // cashback del 5% si tiene 3 meses o menos de registro
            in 4..12 -> subTotal * 0.03 // cashback del 3% si tiene más de 3 meses, pero 12 meses o menos de registro
            else -> 0.0 // no se da cashback si tiene más de 12 meses de registro
        }
    }
}
