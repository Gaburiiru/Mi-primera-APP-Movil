package com.example.tp_parte_2.Login.Crear_user

import Entidades.User
import Repository.UserRepository
import Repository.UserRepository.existe
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.tp_parte_2.R
import java.util.Calendar
import java.text.SimpleDateFormat


class CrearUsuarioActivity : AppCompatActivity() {

    private lateinit var usuario: EditText
    private lateinit var password: EditText
    private lateinit var confirmPw: EditText
    private lateinit var nombre: EditText
    private lateinit var apellido: EditText
    private lateinit var btnCrearUsuario: Button

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        usuario = findViewById(R.id.nombre_de_usuario)
        password = findViewById(R.id.ingrese_contrase_a)
        confirmPw = findViewById(R.id.repetir_contrase_a)
        nombre = findViewById(R.id.nombre)
        apellido = findViewById(R.id.apellido)
        btnCrearUsuario = findViewById(R.id.confirmar)

        btnCrearUsuario.setOnClickListener {

            if(validateInput()) {

                val fecha = Calendar.getInstance()
                val formatoFecha = SimpleDateFormat("dd/MM/yyyy")
                val fechaFormateada = formatoFecha.format(fecha.time)
                val nuevo = User("","","","","",0.0,fechaFormateada.toString())
                val usuario = usuario.text.toString()
                val password = password.text.toString()
                val confirmarPassword = confirmPw.text.toString()
                val nombre = nombre.text.toString()
                val apellido = apellido.text.toString()
                if (nombre.matches("[a-zA-Z]+".toRegex())) {
                    if (apellido.matches("[a-zA-Z]+".toRegex())) {
                        if (!password.equals(confirmarPassword)) {
                            Toast.makeText(this, "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show();
                        } else if (existe(usuario,password)) {
                            Toast.makeText(this, "Nombre de usuario ya existe, cambielo", Toast.LENGTH_SHORT).show();
                        } else {
                            nuevo.nickName = usuario;
                            nuevo.password = password;
                            nuevo.name = nombre;
                            nuevo.surname = apellido;
                            UserRepository.agregar(nuevo);
                            Toast.makeText(this, "Usuario creado correctamente", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(this, "El apellido no puede contener numeros o estar vacio.", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this, "El nombre no puede contener numeros o estar vacio.", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                Toast.makeText(this, "Por favor ingrese los datos solicitados", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun validateInput(): Boolean {
        return usuario.text.isNotEmpty() && password.text.isNotEmpty() && confirmPw.text.isNotEmpty() && nombre.text.isNotEmpty() && apellido.text.isNotEmpty()
    }
}
