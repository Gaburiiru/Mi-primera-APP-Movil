package com.example.tp_parte_2

import NavarFragment
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView

class ComprasActivity : AppCompatActivity(), NavarFragment.OnBotonClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compras)

        val navarFragment = supportFragmentManager.findFragmentById(R.id.fragment_Navar) as? NavarFragment
        navarFragment?.setOnBotonClickListener(this)
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
