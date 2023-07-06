package com.example.tp_parte_2


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit


class PantallaPrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<PrincipalFragment>(R.id.fragmentContainerTransaccion)
        }
    }
}