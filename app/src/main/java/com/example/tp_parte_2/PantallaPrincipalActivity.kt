package com.example.tp_parte_2

import NavarFragment
import Repository.PurchaseRepositoryProvider.Companion.purchasesList
import Repository.UserRepository.session
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_parte_2.Login.ComprasActivity
import com.example.tp_parte_2.adapter.HistorialAdapter

class PantallaPrincipalActivity : AppCompatActivity(), NavarFragment.OnBotonClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        initRecyclerView()

        val navarFragment = supportFragmentManager.findFragmentById(R.id.fragment_Navar) as? NavarFragment
        navarFragment?.setOnBotonClickListener(this)
    }

    private fun initRecyclerView(){
        val userId = session[0].id
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerHistorial)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = HistorialAdapter(userId, purchasesList)
    }

    override fun onInicioClick() {
    }

    override fun onBibliotecaClick() {
        // Implementación deseada para el evento de clic en el botón "Biblioteca"
    }

    override fun onComprasClick() {
        val intent = Intent(this, ComprasActivity::class.java)
        startActivity(intent)
    }

    override fun onRecargaClick() {
        // Implementación deseada para el evento de clic en el botón "Recarga"
    }
}