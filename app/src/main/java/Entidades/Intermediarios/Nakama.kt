package Entidades.Intermediarios

import java.util.*

object Nakama: Intermediario() {

    override fun calcularComision(total: Double): Double {
        val today = Calendar.getInstance()
        if(today.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || today.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){

            return total.times(0.03)
        }else{
            return total.times(0.075)
        }
    }}