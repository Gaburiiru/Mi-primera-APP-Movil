package com.example.tp_parte_2.Login

import Repository.UserRepository
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tp_parte_2.Login.Crear_user.CrearUsuarioActivity
import com.example.tp_parte_2.PantallaPrincipalActivity
import com.example.tp_parte_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Logearse.setOnClickListener {
            val username =  binding.nombreDeUsuario.text.toString()
            val password = binding.textPassword.text.toString()
            if(validateInput()) {
                if(UserRepository.existe(username, password)) {
                    loguear(username, password)
                } else {
                    Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor ingrese los datos solicitados", Toast.LENGTH_SHORT).show()
            }
        }
        binding.registrarse.setOnClickListener{
            crearUsuario()
        }
    }

    private fun loguear(username: String, password: String) {
        val loginMenu = Intent(this, PantallaPrincipalActivity::class.java)
        intent.putExtra("nickname", UserRepository.iniciar(username, password).nickName)
        intent.putExtra("password", UserRepository.iniciar(username, password).password)
        startActivity(loginMenu)
    }

    private fun crearUsuario(){
        val crearUsuarioActivityIntent = Intent(this, CrearUsuarioActivity::class.java)
        startActivity(crearUsuarioActivityIntent)
    }
    private fun validateInput(): Boolean {
        return binding.nombreDeUsuario.text.isNotEmpty() && binding.textPassword.text.isNotEmpty()
    }
}