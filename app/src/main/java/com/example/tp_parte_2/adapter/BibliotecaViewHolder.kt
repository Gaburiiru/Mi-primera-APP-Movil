import Entidades.Game
import Repository.CarritoRepository
import Repository.PurchaseRepositoryProvider.Companion.purchasesList
import Repository.UserRepository.session
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tp_parte_2.R

class BibliotecaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val gameName = view.findViewById<TextView>(R.id.tvGameNameBiblioteca)
    private val gameRelease = view.findViewById<TextView>(R.id.tvReleaseGameBiblioteca)
    private val gameGenres = view.findViewById<TextView>(R.id.tvGenreGameBiblioteca)
    private val gamePrice = view.findViewById<TextView>(R.id.tvPriceGameBiblioteca)
    private val gamePhoto = view.findViewById<ImageView>(R.id.ivGameBiblioteca)
    private val botonComprar = view.findViewById<Button>(R.id.btnComprar)

    fun render(context: Context, juego: Game) {
        val carrito = CarritoRepository.carrito

        gameName.text = juego.name
        gameRelease.text = juego.releaseDate
        gameGenres.text = juego.genre
        gamePrice.text = juego.price.toString()
        Glide.with(gamePhoto.context).load(juego.permalink).into(gamePhoto)

        botonComprar.setOnClickListener {
            val userId = session[0].id
            val gameId = juego.id

            val isGamePurchased = purchasesList.any { it.gameId == gameId && it.userId == userId }

            if (!carrito.contains(juego)) {
                if (!isGamePurchased) {
                    showToast(context, "El juego se agregó al carrito")
                    carrito.add(juego)
                } else {
                    showToast(context, "El usuario ya ha comprado este juego")
                }
            } else {
                showToast(context, "El juego ya está en el carrito")
            }
        }
    }

    private fun showToast(context: Context, message: String) {
        Toast.makeText(context.applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}