package com.example.tp_parte_2.adapter

import Entidades.Purchase
import Repository.GameRepository.games
import Repository.UserRepository
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tp_parte_2.R


class HistorialAdapter(private val userId: String, private val purchasesList: List<Purchase>) : RecyclerView.Adapter<HistorialViewHolder>() {

    override fun getItemCount(): Int {
        val userPurchases = purchasesList.filter { it.userId == userId }
        return userPurchases.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistorialViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HistorialViewHolder(layoutInflater.inflate(R.layout.item_historial, parent, false))
    }

    override fun onBindViewHolder(holder: HistorialViewHolder, position: Int) {
        val userPurchases = purchasesList.filter { it.userId == userId }
        val purchase = userPurchases[position]
        holder.render(purchase)
    }
}