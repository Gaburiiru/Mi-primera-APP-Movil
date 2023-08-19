package com.example.tp_parte_2

import Entidades.Intermediarios.EpicGames
import Entidades.Intermediarios.Nakama
import Entidades.Intermediarios.Steam
import NavbarFragment
import Repository.CarritoRepository.agregarCompra
import Repository.CarritoRepository.carrito
import Repository.CarritoRepository.carritoTotal
import Repository.CarritoRepository.sumaCarrito
import Repository.UserRepository.session
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.example.tp_parte_2.biblioteca.BibliotecaActivity
import com.example.tp_parte_2.databinding.ActivityComprasBinding
import java.util.*

class ComprasActivity : AppCompatActivity(), NavbarFragment.OnBotonClickListener {

    private lateinit var binding: ActivityComprasBinding
    private lateinit var radioGroup: RadioGroup
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComprasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentContainer = findViewById<FragmentContainerView>(R.id.fragment_Navar)
        val navarFragment = NavbarFragment()
        supportFragmentManager.beginTransaction()
            .replace(fragmentContainer.id, navarFragment)
            .commit()
        navarFragment.setOnBotonClickListener(this)

        radioGroup = findViewById(R.id.radioGroup)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.buttonSteam -> {
                    comprarJuego("Steam") { totalAbonar -> Steam.calcularComision(totalAbonar) }
                }
                R.id.buttonEpicStore -> {
                    comprarJuego("Epic Games") { totalAbonar -> EpicGames.calcularComision(totalAbonar) }
                }
                R.id.buttonNakama -> {
                    comprarJuego("Nakama") { totalAbonar -> Nakama.calcularComision(totalAbonar) }
                }
                else -> mostrarMensajeSeleccionaTienda()
            }
        }

        binding.realizarPago.setOnClickListener {
            if (radioGroup.checkedRadioButtonId == -1) {
                mostrarMensajeSeleccionaTienda()
            } else {
                val totalAbonar = sumaCarrito()
                val comision = calcularComisionSeleccionada(totalAbonar)
                val subTotal = comision + totalAbonar
                val createdDate = session[0].createdDate
                val reintegro = CashbackProvider.cashback(subTotal, createdDate)

                if (reintegro == 0.0) {
                    binding.cashback.text = "NO APLICA"
                } else {
                    binding.cashback.text = String.format("%.2f", reintegro)
                }
                binding.total.text = String.format("%.2f",(subTotal).minus(reintegro))

                showToast("Tienda seleccionada: ${obtenerTiendaSeleccionada()}")

                if (session[0].money >= totalAbonar) {
                    session[0].restarMoney(subTotal.minus(reintegro))
                    agregarCompra()
                    // Limpiar carrito y carritoTotal
                    carrito.clear()
                    carritoTotal.clear()
                    binding.comision.text = ""
                    binding.cashback.text = ""
                    binding.total.text = "0.00"

                    showToast("Compra realizada exitosamente")
                } else {
                    showToast("Debe recargar el saldo")
                }
            }
        }
    }

    private fun comprarJuego(tienda: String, comisionCalculator: (Double) -> Double) {
        if (carritoTotal.isNotEmpty() && carritoTotal[0] > 0) {
            val totalAbonar = sumaCarrito()
            val comision = comisionCalculator(totalAbonar)
            binding.comision.text = String.format("%.2f", comision)

            val subTotal = comision + totalAbonar
            val createdDate = session[0].createdDate

            val reintegro = CashbackProvider.cashback(subTotal, createdDate)

            if (reintegro == 0.0) {
                binding.cashback.text = "NO APLICA"
            } else {
                binding.cashback.text = String.format("%.2f", reintegro)
            }
            binding.total.text = String.format("%.2f", subTotal.minus(reintegro))

            showToast("Tienda seleccionada: $tienda")
        } else {
            showToast("Por favor, seleccione un juego.")
        }
    }

    private fun mostrarMensajeSeleccionaTienda() {
        showToast("Por favor, seleccione una tienda")
    }

    private fun calcularComisionSeleccionada(totalAbonar: Double): Double {
        return when (obtenerTiendaSeleccionada()) {
            "Steam" -> Steam.calcularComision(totalAbonar)
            "Epic Games" -> EpicGames.calcularComision(totalAbonar)
            "Nakama" -> Nakama.calcularComision(totalAbonar)
            else -> 0.0
        }
    }
    private fun obtenerTiendaSeleccionada(): String {
        return when (radioGroup.checkedRadioButtonId) {
            R.id.buttonSteam -> "Steam"
            R.id.buttonEpicStore -> "Epic Games"
            R.id.buttonNakama -> "Nakama"
            else -> ""
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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