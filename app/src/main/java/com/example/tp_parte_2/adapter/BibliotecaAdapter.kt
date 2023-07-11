package com.example.tp_parte_2.adapter

import BibliotecaViewHolder
import android.view.LayoutInflater
import Entidades.Game
import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_parte_2.R


class BibliotecaAdapter(private val context: Context, private val gameList: List<Game>) : RecyclerView.Adapter<BibliotecaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BibliotecaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BibliotecaViewHolder(layoutInflater.inflate(R.layout.item_biblioteca, parent, false))
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    override fun onBindViewHolder(holder: BibliotecaViewHolder, position: Int) {
        val game = gameList[position]
        holder.render(context, game)
    }
}