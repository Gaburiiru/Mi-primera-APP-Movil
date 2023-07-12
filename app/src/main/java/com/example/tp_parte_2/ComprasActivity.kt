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

class ComprasActivity : AppCompatActivity(), NavbarFragment.OnBotonClickListener {

    private lateinit var binding: ActivityComprasBinding
    private lateinit var radioGroup: RadioGroup

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?)    {
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
                else -> "Porfavor seleccione una tienda"
            }
        }
    }
    fun comprarJuego(tienda: String, comisionCalculator: (Double) -> Double) {
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
            binding.total.text = String.format("%.2f",totalAbonar.plus(comision).minus(reintegro))

            showToast("Tienda seleccionada: $tienda")

            binding.realizarPago.setOnClickListener(){
                if (session[0].money >= totalAbonar){
                    session[0].restarMoney(totalAbonar)
                    agregarCompra()

                    // Limpiar carrito y carritoTotal
                    carrito.clear()
                    carritoTotal.clear()

                    binding.comision.text = ""
                    binding.cashback.text = ""
                    binding.total.text = ""
                }else{
                    showToast("Debe recargar el saldo")
                }
            }
        } else {
            showToast("Por favor, seleccione un juego.")
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