package com.example.tp_parte_2.biblioteca

import NavarFragment
import Repository.GameRepository
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.tp_parte_2.ComprasActivity
import com.example.tp_parte_2.PantallaPrincipalActivity
import com.example.tp_parte_2.R
import com.example.tp_parte_2.RecargarActivity
import com.example.tp_parte_2.adapter.BibliotecaAdapter
class BibliotecaActivity : AppCompatActivity(), NavarFragment.OnBotonClickListener {

    private lateinit var btnCarrito: Button
    private lateinit var recyclerViewBiblioteca: RecyclerView
    private lateinit var bibliotecaAdapter: BibliotecaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biblioteca)

        val navarFragment = supportFragmentManager.findFragmentById(R.id.fragment_Navar) as? NavarFragment
        navarFragment?.setOnBotonClickListener(this)

        initRecyclerView()

        btnCarrito = findViewById(R.id.botonCarrito)
        btnCarrito.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        recyclerViewBiblioteca = findViewById(R.id.recyrclerViewBiblioteca)
        recyclerViewBiblioteca.layoutManager = LinearLayoutManager(this)
        bibliotecaAdapter = BibliotecaAdapter(this, GameRepository.games)
        recyclerViewBiblioteca.adapter = bibliotecaAdapter
    }
    override fun onInicioClick() {
        val intent = Intent(this, PantallaPrincipalActivity::class.java)
        startActivity(intent)
    }

    override fun onBibliotecaClick() {
    }

    override fun onComprasClick() {
        val intent = Intent(this, ComprasActivity::class.java)
        startActivity(intent)
    }

    override fun onRecargaClick() {
        val intent = Intent(this, RecargarActivity::class.java)
        startActivity(intent)
    }
}