package Entidades.Intermediarios


import org.threeten.bp.LocalTime


object EpicGames: Intermediario(){

    init{
        _nombreIntermediario = TiendaDeCompras.EPICGAMES.toString()
    }
    override fun calcularComision(): Double {

        if(LocalTime.now() in LocalTime.of(20,0,0,0).. LocalTime.of(23,59,0,0)){
            return 0.01
        }else{
            return 0.03
        }
    }
}