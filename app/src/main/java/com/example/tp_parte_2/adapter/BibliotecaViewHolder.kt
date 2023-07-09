package com.example.tp_parte_2.adapter

import Entidades.Game
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tp_parte_2.R

class BibliotecaViewHolder (view:View):RecyclerView.ViewHolder(view){

    val gameName = view.findViewById<TextView>(R.id.tvGameNameBiblioteca)
    val gameRelease = view.findViewById<TextView>(R.id.tvReleaseGameBiblioteca)
    val gameGenres = view.findViewById<TextView>(R.id.tvGenreGameBiblioteca)
    val gamePrice = view.findViewById<TextView>(R.id.tvPriceGameBiblioteca)
    val gamePhoto = view.findViewById<ImageView>(R.id.ivGameBiblioteca)

    fun render(game: Game){
        gameName.text = game.name
        gameRelease.text = game.releaseDate
        gameGenres.text = game.genre
        gamePrice.text = game.price.toString()
        Glide.with(gamePhoto.context).load(game.permalink).into(gamePhoto)
    }

}