package com.example.tp_parte_2.adapter

import Entidades.Purchase
import Repository.GameRepository.games
import Repository.PurchaseRepositoryProvider.Companion.purchasesList
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tp_parte_2.R

class HistorialViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val nameGame = view.findViewById<TextView>(R.id.tvNameGame)
    private val precioGame = view.findViewById<TextView>(R.id.tvPrecio)
    private val fechaCompra = view.findViewById<TextView>(R.id.tvFecha)
    private val horaCompra = view.findViewById<TextView>(R.id.tvHora)
    private val photo = view.findViewById<ImageView>(R.id.ivGame)

    fun render(purchase: Purchase) {
        val game = games.find { it.id == purchase.gameId }
        if (game != null) {
            nameGame.text = game.name
            precioGame.text = purchase.amount.toString()
            fechaCompra.text = purchase.createdDate
            horaCompra.text = purchase.timeDate
            Glide.with(photo.context).load(game.permalink).into(photo)
        }
    }
}
