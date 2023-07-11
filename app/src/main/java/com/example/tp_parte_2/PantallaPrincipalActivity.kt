package com.example.tp_parte_2

import Entidades.User
import NavarFragment
import Repository.PurchaseRepositoryProvider.Companion.purchasesList
import Repository.UserRepository
import Repository.UserRepository.session
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_parte_2.adapter.HistorialAdapter
import com.example.tp_parte_2.biblioteca.BibliotecaActivity

class PantallaPrincipalActivity : AppCompatActivity(), NavarFragment.OnBotonClickListener {
    private lateinit var nickNameInfo: TextView
    private lateinit var dateInfo: TextView
    private lateinit var moneyInfo: TextView
    private lateinit var currentUser: User

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        initRecyclerView()

        val navarFragment = supportFragmentManager.findFragmentById(R.id.fragment_Navar) as? NavarFragment
        navarFragment?.setOnBotonClickListener(this)


        currentUser = session[0]
        nickNameInfo = findViewById(R.id.userInfo)
        dateInfo = findViewById(R.id.dateInfo)
        moneyInfo = findViewById(R.id.moneyInfo)

        for (user in UserRepository.users){
            if (user.id == currentUser.id){
                nickNameInfo.text = user.nickName
                dateInfo.text = user.createdDate
                moneyInfo.text = String.format("%.2f", user.money)
            }
        }
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