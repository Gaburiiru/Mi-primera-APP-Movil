package Entidades.Intermediarios

import java.util.*

object Nakama: Intermediario() {
    init{
        _nombreIntermediario = TiendaDeCompras.NAKAMA.toString()
    }
    override fun calcularComision(): Double {
        val today = Calendar.getInstance()
        if(today.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || today.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){

            return 0.03
        }else{
            return 0.075
        }
    }}