package Entidades.Intermediarios

object  Steam : Intermediario() {

    init{
        _nombreIntermediario = TiendaDeCompras.STEAM.toString()
    }
    override fun calcularComision(): Double {
        return 0.02
    }
}