package com.example.recyclerviewexamplo

import Entidades.Game
import NavarFragment
import Repository.CarritoRepository
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewexamplo.adapter.CarritoAdapter
import com.example.tp_parte_2.ComprasActivity
import com.example.tp_parte_2.PantallaPrincipalActivity
import com.example.tp_parte_2.R
import com.example.tp_parte_2.RecargarActivity
import com.example.tp_parte_2.biblioteca.BibliotecaActivity
import com.example.tp_parte_2.databinding.ActivityCarritoBinding

class CarritoActivity : AppCompatActivity(), NavarFragment.OnBotonClickListener {

    private lateinit var binding: ActivityCarritoBinding
    private var carritoMutableList: MutableList<Game> =
        CarritoRepository.carrito.toMutableList()
    private lateinit var adapter: CarritoAdapter
    private val llmanager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarritoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()

        val navarFragment = supportFragmentManager.findFragmentById(R.id.fragment_Navar) as? NavarFragment
        navarFragment?.setOnBotonClickListener(this)
    }

    private fun initRecyclerView() {
        adapter = CarritoAdapter(
            carritoList = carritoMutableList,
            onClickDelete = { position -> onDeletedItem(position) }
        )
        binding.recyrclerViewCarrito.layoutManager = llmanager
        binding.recyrclerViewCarrito.adapter = adapter

    }
    private fun onDeletedItem(position: Int) {
        if (position >= 0 && position < carritoMutableList.size) {
            carritoMutableList.removeAt(position)
            adapter.notifyItemRemoved(position)
        }
    }
    override fun onInicioClick() {
        val intent = Intent(this, PantallaPrincipalActivity::class.java)
        startActivity(intent)
    }

    override fun onBibliotecaClick() {
        val intent = Intent(this, BibliotecaActivity::class.java)
        startActivity(intent)
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