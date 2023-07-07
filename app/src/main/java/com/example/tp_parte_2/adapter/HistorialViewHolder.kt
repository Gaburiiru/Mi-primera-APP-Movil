package com.example.tp_parte_2.adapter

import Entidades.Game
import Entidades.Purchase
import Repository.GameRepository.games
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tp_parte_2.R

class HistorialViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val nameGame = view.findViewById<TextView>(R.id.tvNameGame)
    val precioGame = view.findViewById<TextView>(R.id.tvPrecio)
    val fechaCompra = view.findViewById<TextView>(R.id.tvFecha)
    val horaCompra = view.findViewById<TextView>(R.id.tvHora)
    val photo = view.findViewById<ImageView>(R.id.ivGame)

    fun render(purchase: Purchase) {
        val game = findGameById(purchase.gameId)
        nameGame.text = game?.name
        precioGame.text = purchase.amount.toString()
        fechaCompra.text = purchase.createdDate
        horaCompra.text = purchase.timeDate
        Glide.with(photo.context).load(game?.permalink).into(photo)
    }

    private fun findGameById(gameId: Long): Game? {
        return games.find { it.id == gameId }
    }
}