package Entidades.Intermediarios


import org.threeten.bp.LocalTime


object EpicGames: Intermediario(){

    override fun calcularComision(total: Double): Double {

        if(LocalTime.now() in LocalTime.of(20,0,0,0).. LocalTime.of(23,59,0,0)){
            return total.times(0.01)
        }else{
            return total.times(0.03)
        }
    }
}