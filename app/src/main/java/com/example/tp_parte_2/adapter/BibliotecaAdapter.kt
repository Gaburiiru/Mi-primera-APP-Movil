package com.example.tp_parte_2.adapter

import android.view.LayoutInflater
import Entidades.Game
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_parte_2.R


class BibliotecaAdapter(private val bibliotecaList:List<Game>): RecyclerView.Adapter<BibliotecaViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BibliotecaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BibliotecaViewHolder(layoutInflater.inflate(R.layout.item_biblioteca,parent,false))
    }

    override fun getItemCount(): Int {
        return bibliotecaList.size
    }

    override fun onBindViewHolder(holder: BibliotecaViewHolder, position: Int) {
        val item = bibliotecaList[position]
        holder.render(item)
    }
}