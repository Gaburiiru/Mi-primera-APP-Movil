package com.example.tp_parte_2

import NavbarFragment
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tp_parte_2.biblioteca.BibliotecaActivity

class ComprasActivity : AppCompatActivity(), NavbarFragment.OnBotonClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compras)

        val navarFragment = supportFragmentManager.findFragmentById(R.id.fragment_Navar) as? NavbarFragment
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
    }

    override fun onRecargaClick() {
        val intent = Intent(this, RecargarActivity::class.java)
        startActivity(intent)
    }
}
