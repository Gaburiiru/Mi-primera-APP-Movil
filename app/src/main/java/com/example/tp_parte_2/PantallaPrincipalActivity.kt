package com.example.tp_parte_2


import Repository.PurchaseRepository
import Repository.PurchaseRepositoryProvider
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_parte_2.adapter.HistorialAdapter


class PantallaPrincipalActivity : AppCompatActivity() {

    private lateinit var btnInicio: Button
    private lateinit var btnBiblioteca: Button
    private lateinit var btnCompras: Button
    private lateinit var btnRecarga: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        initRecyclerView()

        btnInicio = findViewById(R.id.inicio)
        btnBiblioteca = findViewById(R.id.biblioteca)
        btnCompras = findViewById(R.id.compras)
        btnRecarga = findViewById(R.id.recargar)

/*        btnBiblioteca.setOnClickListener {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<PrincipalFragment>(R.id.fragmentContainerHistorial)
                PurchaseRepository.purchasesList
            }
        }*/
    }
    private fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerHistorial)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = HistorialAdapter(PurchaseRepositoryProvider.purchasesList)
    }
}