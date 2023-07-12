package com.example.tp_parte_2.biblioteca

import Entidades.Game
import NavbarFragment
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
import com.example.tp_parte_2.databinding.ActivityCarritoBinding

class CarritoActivity : AppCompatActivity(), NavbarFragment.OnBotonClickListener {

    private lateinit var binding: ActivityCarritoBinding
    private lateinit var carritoMutableList: MutableList<Game>
    private lateinit var adapter: CarritoAdapter
    private val llmanager = LinearLayoutManager(this)
    private var carritoTotal: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarritoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()

        val navarFragment = supportFragmentManager.findFragmentById(R.id.fragment_Navar) as? NavbarFragment
        navarFragment?.setOnBotonClickListener(this)

        updateTotalApagar()
    }

    private fun initRecyclerView() {
        carritoMutableList = CarritoRepository.carrito.toMutableList()
        adapter = CarritoAdapter(
            carritoList = carritoMutableList,
            onClickDelete = { position -> onDeletedItem(position) }
        )
        binding.recyrclerViewCarrito.layoutManager = llmanager
        binding.recyrclerViewCarrito.adapter = adapter
    }

    private fun onDeletedItem(position: Int) {
        carritoMutableList.removeAt(position)
        adapter.notifyItemRemoved(position)
        updateTotalApagar()
    }

    private fun updateTotalApagar() {
        carritoTotal = carritoMutableList.sumByDouble { it.price }
        binding.totalApagar.text = String.format("%.2f", carritoTotal)
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