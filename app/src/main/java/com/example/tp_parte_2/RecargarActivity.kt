package com.example.tp_parte_2

import NavbarFragment
import Repository.UserRepository.session
import Repository.UserRepository.users
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import Entidades.User
import android.content.Context
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.example.tp_parte_2.biblioteca.BibliotecaActivity
import com.example.tp_parte_2.databinding.ActivityRecargarBinding

class RecargarActivity : AppCompatActivity(), NavbarFragment.OnBotonClickListener {
    private lateinit var saldo: TextView
    private lateinit var recargarMonto: EditText
    private lateinit var currentUser: User
    private lateinit var binding: ActivityRecargarBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recargar)
        binding = ActivityRecargarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        currentUser = session[0]

        val fragmentContainer = findViewById<FragmentContainerView>(R.id.fragment_Navar)
        val navarFragment = NavbarFragment()
        supportFragmentManager.beginTransaction()
            .replace(fragmentContainer.id, navarFragment)
            .commit()
        navarFragment.setOnBotonClickListener(this)

        saldo = findViewById(R.id.saldo)

        for (user in users){
            if (user.id == currentUser.id){
                saldo.text = user.money.toString()
            }
        }

        recargarMonto = findViewById(R.id.RecargarMonto)

        saldo.text = String.format("%.2f", currentUser.money)

        val buttonRecargar = findViewById<Button>(R.id.btnRecargar)
        buttonRecargar.setOnClickListener {
            val montoRecarga = recargarMonto.text.toString().toDoubleOrNull()
            if (montoRecarga != null && montoRecarga > 0 && "-?\\d+(\\.\\d+)?".toRegex().matches(recargarMonto.text.toString())) {
                currentUser.recargarMoney(montoRecarga)
                saldo.text = String.format("%.2f", currentUser.money)
                recargarMonto.setText("")
            } else {
                Toast.makeText(this, "El monto ingresado no es válido.", Toast.LENGTH_SHORT).show()
            }
        }
        binding.RecargarMonto.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || event.keyCode == KeyEvent.KEYCODE_ENTER) {
                // Ocultar el teclado
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.RecargarMonto.windowToken, 0)
                true
            } else {
                false
            }
        }
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
    }
}