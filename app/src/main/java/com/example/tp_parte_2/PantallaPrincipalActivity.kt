package com.example.tp_parte_2


import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit


class PantallaPrincipalActivity : AppCompatActivity() {

    private lateinit var btnInicio: Button
    private lateinit var btnBiblioteca: Button
    private lateinit var btnCompras: Button
    private lateinit var btnRecarga: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

  /*      supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<PrincipalFragment>(R.id.fragmentContainerTransaccion)
        }
*/
        btnInicio = findViewById(R.id.inicio)
        btnBiblioteca = findViewById(R.id.biblioteca)
        btnCompras = findViewById(R.id.compras)
        btnRecarga = findViewById(R.id.recargar)

        btnBiblioteca.setOnClickListener {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<PrincipalFragment>(R.id.fragmentContainerTransaccion)

            }
        }
    }
}