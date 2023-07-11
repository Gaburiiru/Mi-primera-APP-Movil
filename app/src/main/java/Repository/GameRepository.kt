package Repository

import Entidades.Game
import Repository.PurchaseRepository.getBiblioteca
import Repository.PurchaseRepository.purchases
import Repository.UserRepository.session

object GameRepository {
    var idGame: Long = 0
    private var costoGame: Double = 0.0
    private fun selectOption(): Char {
        return readln()[0]
    }
    val games = mutableListOf<Game>()
    private var number: MutableList<Int> = mutableListOf()
    init {
        games.add(Game(1L, "Resident Evil 4", "2005", "Survival Horror", 350.00, "https://media.vandal.net/m/67022/resident-evil-4-20193420315720_1.jpg"))
        games.add(Game(2L, "Minecraft", "2011", "Sandbox", 150.75, "https://www.proandroid.com/wp-content/uploads/2016/12/minecraft.jpg"))
        games.add(Game(3L, "FIFA 23", "2022", "Deporte", 1800.00, "https://sm.ign.com/ign_es/screenshot/default/image002-1_3te9.jpg"))
        games.add(Game(4L, "Silent Hill 3", "2003", "Survival Horror", 70.00, "https://m.media-amazon.com/images/I/91AQS6et4QL._AC_UF894,1000_QL80_.jpg"))
        games.add(Game(5L, "Call of Duty: Black Ops 2", "2012", "Shooter", 600.00, "https://cdn.game.tv/game-tv-content/images_3/41666c262d8f858a9ebe021ac4dbadb5/41666c262d8f858a9ebe021ac4dbadb5/GameTile.jpg"))
        games.add(Game(6L, "Mortal Kombat 11", "2019", "Pelea", 315.50, "https://as01.epimg.net/meristation/imagenes/2019/01/10/noticias/1547149102_234157_1547149165_noticia_normal.jpg"))
        games.add(Game(7L, "The Last of Us 2", "2020", "Survival Horror", 8500.00, "https://image.api.playstation.com/vulcan/img/rnd/202010/2618/w48z6bzefZPrRcJHc7L8SO66.png"))
        games.add(Game(8L, "Gran Turismo 7", "2022", "Carreras", 800.00, "https://edicion.parentesis.com:8082/imagesPosts/gt00.jpg"))
        games.add(Game(9L, "Pokemon: Let's Go Pikachu!", "2018", "Aventura", 210.20, "https://juegosdigitalesargentina.com/files/images/productos/1639183282-pokemon-lets-go-pikachu-nintendo-switch.jpg"))
        games.add(Game(10L, "GTA San Andreas", "2004", "Accion", 1800.00, "https://media.vandal.net/m/3903/2005610224436_1.jpg"))
        games.add(Game(11L, "Mario Kart 8", "2014", "Carreras", 6500.00, "https://media.vandal.net/m/45256/mario-kart-8-deluxe-201742811181_45.jpg"))
        games.add(Game(12L, "Dark Souls 3", "2016", "Accion", 50.75, "https://as.com/meristation/imagenes/2020/04/07/game_cover/136602131586253551.jpg"))
        games.add(Game(13L, "God of War: Ragnarok", "2022", "Aventura", 5350.00, "https://assets-prd.ignimgs.com/2022/07/25/9781506733494-1658716557072.jpg"))
    }
    fun getGame() {
        var validacionMenu = true
        var EstadoDeBiblioteca = ""
        for ((indice)in games.withIndex()){
            number.add(indice + 1)
        }

        if (EstadoDeBiblioteca.isNotEmpty()) {
            println(EstadoDeBiblioteca)
            validacionMenu = false
        }

        // Si bandera es verdadera, continuar con el flujo del programa
        if (validacionMenu) {
            // Imprimir el menú principal
            println("--------------------------------------")
            println("1-¿Quiere comprar uno de los juegos?")
            println("2-Lista de juegos disponibles")
            println("3-Ver tu Biblioteca de juegos")
            println("4-Salir")
            println("--------------------------------------")

            when (readlnOrNull()) {
                "1" -> {
                    println("Ingrese el número del juego que desea comprar:")
                    val juegoSeleccionado = readlnOrNull()

                    if (juegoSeleccionado != null && !juegoSeleccionado.matches("-?\\d+(\\.\\d+)?".toRegex())) {
                        println("Ingrese un numero de juego valido.")
                        getGame()
                    } else {
                        if (!number.contains(juegoSeleccionado?.toInt())) {
                            EstadoDeBiblioteca = "El juego seleccionado no existe."
                        } else {
                            val juegoRepetido = purchases.find { it.userId == session[0].id && it.gameId == juegoSeleccionado?.toLong() }
                            if (juegoRepetido != null) {
                                EstadoDeBiblioteca = "El juego seleccionado ya ah sido comprado."
                            } else {
                                getById(juegoSeleccionado)
                            }
                        }
                        if (EstadoDeBiblioteca.isNotEmpty()) {
                            println(EstadoDeBiblioteca)
                        }
                        getGame()
                    }
                }
                "2" -> {
                    listGames()
                    getGame()
                }
                "3" -> {
                    getBiblioteca()
                    getGame()
                }
                "4" -> {
                    // Salir del programa
                    println("Gracias por utilizar nuestro servicio!")
                }
                else -> {
                    println("Opción inválida.Por favor, seleccione una opción válida.")
                    getGame()
                }
            }
        }
    }

    fun listGames(){
        println("Lista de juegos disponibles:")

        games.sortedBy { it.id }.forEach {
            println("Id ${it.id}\nName: ${it.name}\nRelease Year: ${it.releaseDate}\nGenre: ${it.genre}\nPrice: $${it.price}\nImage URL: ${it.permalink}\n")
        }
    }

    fun getById(juego: String?) {
        var validacionIntermediario: Boolean
        var j = juego?.toInt()
        j = j?.minus(1)
        println("El juego elegido es: ${games[j!!].name} y su precio es de: ${games[j].price}")
        println("¿estas seguro? \n 1-si \n 2-no")
        var confirmarCompra = readlnOrNull()
        do {
            when(confirmarCompra) {
                1.toString() -> {
                    validacionIntermediario = true
                    for (user in session) {
                        if (user.money >= games[j].price){
                            println("Ingrese por que plataforma desea obtenerlo:")
                            println(
                                """
                                    1- STEAM
                                    2- EPIC GAMES
                                    3- NAKAMA
                                    Ingrese su opcion: 
                                    """.trimIndent()
                            )
                            val option = selectOption()
                            costoGame=games[j].price
                            idGame = games[j].id
                            val instanciaPurchaseGameService = PurchaseGameService()
                            when (option) {
                                '1' -> {
                                    instanciaPurchaseGameService.compraSteam(user, costoGame)
                                    println("compra realizada con exito")
                                }
                                '2' -> {
                                    instanciaPurchaseGameService.compraEpicGames(user, costoGame)
                                    println("compra realizada con exito")
                                }
                                '3' -> {
                                    instanciaPurchaseGameService.compraNakama(user,costoGame)
                                    println("compra realizada con exito")
                                }
                                else -> println("ingrese una opcion valida")
                            }
                        }else{
                            println("no tenes plata pibe")
                        }
                    }
                }
                2.toString() -> {
                    validacionIntermediario = true
                }
                else -> {
                    println("ingrese una opcion valida: \n ¿estas seguro? \n 1-si \n 2-no")
                    confirmarCompra = readlnOrNull()
                    validacionIntermediario = false
                }
            }
        }while (!validacionIntermediario)
    }
}