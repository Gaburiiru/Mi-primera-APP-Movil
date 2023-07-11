package Repository

import Entidades.Game
object GameRepository {
    val games = mutableListOf<Game>()
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
}
