import Entidades.Game
import Repository.CarritoRepository
import Repository.PurchaseRepositoryProvider.Companion.purchasesList
import Repository.UserRepository.session
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tp_parte_2.databinding.ItemBibliotecaBinding

class BibliotecaViewHolder(private val context: Context, view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemBibliotecaBinding.bind(view)

    fun render(
        context: Context,
        game: Game
    ) {
        var acumulador: Double = 0.0
        val carrito = CarritoRepository.carrito

        binding.tvGameNameBiblioteca.text = game.name
        binding.tvReleaseGameBiblioteca.text = game.releaseDate
        binding.tvGenreGameBiblioteca.text = game.genre
        binding.tvPriceGameBiblioteca.text = game.price.toString()
        Glide.with(binding.ivGameBiblioteca.context).load(game.permalink)
            .into(binding.ivGameBiblioteca)
        binding.btnComprar.setOnClickListener {
            val userId = session[0].id
            val gameId = game.id

            val isGamePurchased = purchasesList.any { it.gameId == gameId && it.userId == userId }

            if (!carrito.contains(game)) {
                if (!isGamePurchased) {
                    showToast("El juego se agregó al carrito")
                    carrito.add(game)
                    acumulador += game.price
                    CarritoRepository.carritoTotal.add(acumulador)
                } else {
                    showToast("El usuario ya ha comprado este juego")
                }
            } else {
                showToast("El juego ya está en el carrito")
            }
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(context.applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}