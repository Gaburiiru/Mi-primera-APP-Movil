package com.example.tp_parte_2.adapter

import Entidades.Game
import Repository.CarritoRepository.carrito
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tp_parte_2.databinding.ItemCarritoBinding

class CarritoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemCarritoBinding.bind(view)

    fun render(
        Carrito: Game,
        onClickDelete: (Int) -> Unit
    ) {
        binding.tvGameNameCarrito.text = Carrito.name
        binding.tvReleaseGameCarrito.text = Carrito.releaseDate
        binding.tvGenreGameCarrito.text = Carrito.genre
        binding.tvPriceGameCarrito.text = Carrito.price.toString()
        Glide.with(binding.ivGameCarrito.context).load(Carrito.permalink).into(binding.ivGameCarrito)
        binding.btnEliminar.setOnClickListener {
            onClickDelete(adapterPosition)
            carrito.remove(Carrito)
        }
    }
}