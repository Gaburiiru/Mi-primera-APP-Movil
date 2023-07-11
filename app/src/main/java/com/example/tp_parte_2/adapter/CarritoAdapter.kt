package com.example.recyclerviewexamplo.adapter

import Entidades.Game
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_parte_2.R
import com.example.tp_parte_2.adapter.CarritoViewHolder

class CarritoAdapter(
    private var carritoList: List<Game>,
    private val onClickDelete:(Int) -> Unit
) : RecyclerView.Adapter<CarritoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarritoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CarritoViewHolder(layoutInflater.inflate(R.layout.item_carrito, parent, false))
    }

    override fun onBindViewHolder(holder: CarritoViewHolder, position: Int) {
        val item = carritoList[position]
        holder.render(item, onClickDelete)
    }

    override fun getItemCount(): Int = carritoList.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateCarrito(carritoList: List<Game>){
        this.carritoList = carritoList
        notifyDataSetChanged()
    }
}