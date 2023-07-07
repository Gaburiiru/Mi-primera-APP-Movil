package com.example.tp_parte_2.adapter

import Entidades.Purchase
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_parte_2.R

class HistorialAdapter(private val purchasesList:List<Purchase>) : RecyclerView.Adapter<HistorialViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistorialViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HistorialViewHolder(layoutInflater.inflate(R.layout.item_historial,parent,false))
    }

    //devuelve el tamaño del RecyclerView
    override fun getItemCount(): Int {
        return purchasesList.size
    }

    override fun onBindViewHolder(holder: HistorialViewHolder, position: Int) {
        val item = purchasesList[position]
        holder.render(item)
    }

}