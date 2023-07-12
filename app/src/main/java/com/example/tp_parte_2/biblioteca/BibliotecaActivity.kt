package com.example.tp_parte_2.biblioteca

import Entidades.Game
import NavbarFragment
import Repository.GameRepository
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.FragmentContainerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp_parte_2.ComprasActivity
import com.example.tp_parte_2.PantallaPrincipalActivity
import com.example.tp_parte_2.R
import com.example.tp_parte_2.RecargarActivity
import com.example.tp_parte_2.adapter.BibliotecaAdapter
import com.example.tp_parte_2.databinding.ActivityBibliotecaBinding

class BibliotecaActivity : AppCompatActivity(), NavbarFragment.OnBotonClickListener {

    private lateinit var btnCarrito: Button
    private lateinit var binding: ActivityBibliotecaBinding
    private var gameMutableList: MutableList<Game> =
        GameRepository.games.toMutableList()
    private lateinit var adapter: BibliotecaAdapter
    private val llmanager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biblioteca)
        binding = ActivityBibliotecaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.etBuscar.addTextChangedListener { userFilter ->
            val juegosFiltrados =
                gameMutableList.filter { game ->
                    game.name.lowercase().contains(userFilter.toString().lowercase())
                }
            adapter.updateGameList(juegosFiltrados)
        }
        binding.etBuscar.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || event.keyCode == KeyEvent.KEYCODE_ENTER) {
                // Ocultar el teclado
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.etBuscar.windowToken, 0)
                true
            } else {
                false
            }
        }

        val fragmentContainer = findViewById<FragmentContainerView>(R.id.fragment_Navar)
        val navarFragment = NavbarFragment()
        supportFragmentManager.beginTransaction()
            .replace(fragmentContainer.id, navarFragment)
            .commit()
        navarFragment.setOnBotonClickListener(this)

        initRecyclerView()

        btnCarrito = findViewById(R.id.botonCarrito)
        btnCarrito.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            startActivity(intent)
        }
    }
    private fun initRecyclerView() {
        adapter = BibliotecaAdapter(
            context = this,
            gameList = gameMutableList
        )
        binding.recyrclerViewBiblioteca.layoutManager = llmanager
        binding.recyrclerViewBiblioteca.adapter = adapter
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