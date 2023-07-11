package com.example.tp_parte_2.adapter

import BibliotecaViewHolder
import Entidades.Game
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_parte_2.R

class BibliotecaAdapter(
    private val context: Context,
    private var gameList: List<Game>
) : RecyclerView.Adapter<BibliotecaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BibliotecaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_biblioteca, parent, false)
        return BibliotecaViewHolder(context, view)
    }

    override fun onBindViewHolder(holder: BibliotecaViewHolder, position: Int) {
        val item = gameList[position]
        holder.render(context, item)
    }

    override fun getItemCount(): Int = gameList.size

    fun updateGameList(newGameList: List<Game>) {
        gameList = newGameList
        notifyDataSetChanged()
    }
}