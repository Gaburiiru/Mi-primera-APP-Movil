package com.example.tp_parte_2


import Repository.PurchaseRepository
import Repository.PurchaseRepositoryProvider
import Repository.UserRepository
import Repository.UserRepository.session
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_parte_2.R.id.userFragment
import com.example.tp_parte_2.WelcomeFragment.Companion.CREATEDDATE_BUNDLE
import com.example.tp_parte_2.WelcomeFragment.Companion.MONEY_BUNDLE
import com.example.tp_parte_2.WelcomeFragment.Companion.NICKNAME_BUNDLE
import com.example.tp_parte_2.adapter.HistorialAdapter


class PantallaPrincipalActivity : AppCompatActivity() {

    private lateinit var btnInicio: Button
    private lateinit var btnBiblioteca: Button
    private lateinit var btnCompras: Button
    private lateinit var btnRecarga: Button


    private lateinit var nickName: TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        initRecyclerView()

        btnInicio = findViewById(R.id.inicio)
        btnBiblioteca = findViewById(R.id.biblioteca)
        btnCompras = findViewById(R.id.compras)
        btnRecarga = findViewById(R.id.recargar)

        val userSesion= session[0]

        nickName = findViewById(R.id.userFragment)

        nickName.text = userSesion.nickName

       /* val bundle = bundleOf(
            NICKNAME_BUNDLE to userSesion.nickName,
            CREATEDDATE_BUNDLE to userSesion.createdDate,
            MONEY_BUNDLE to userSesion.money)
      supportFragmentManager.commit {
          setReorderingAllowed(true)
          add<WelcomeFragment>(R.id.fragmentContainer, args = bundle)
      }
*/
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