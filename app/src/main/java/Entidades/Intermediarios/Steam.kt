package Entidades.Intermediarios

object  Steam : Intermediario() {

    override fun calcularComision(total: Double): Double {
        return total.times(0.02)
    }
}