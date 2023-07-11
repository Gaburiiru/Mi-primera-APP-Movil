package Entidades.Intermediarios

open abstract class Intermediario {

    var _nombreIntermediario: String = ""


        get() {
            return _nombreIntermediario
        }

    abstract fun calcularComision(): Double

}