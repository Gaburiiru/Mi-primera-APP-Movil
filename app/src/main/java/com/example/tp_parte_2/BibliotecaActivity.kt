package com.example.tp_parte_2

import NavarFragment
import Repository.GameRepository
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_parte_2.adapter.BibliotecaAdapter

class BibliotecaActivity : AppCompatActivity(), NavarFragment.OnBotonClickListener {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biblioteca)

        val navarFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_Navar) as? NavarFragment
        navarFragment?.setOnBotonClickListener(this)

        initRecyreclerView()

    }
    fun initRecyreclerView(){
            val recyrcleView = findViewById<RecyclerView>(R.id.recyrclerViewBiblioteca)
            recyrcleView.layoutManager = LinearLayoutManager(this)
            recyrcleView.adapter = BibliotecaAdapter(GameRepository.games)
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