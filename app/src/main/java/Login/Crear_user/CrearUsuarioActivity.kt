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
import java.time.LocalDate

class CrearUsuarioActivity : AppCompatActivity() {

    private lateinit var usuario: EditText
    private lateinit var password: EditText
    private lateinit var confirmPw: EditText
    private lateinit var nombre: EditText
    private lateinit var apellido: EditText
    private lateinit var money: EditText
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
        money = findViewById(R.id.ingresar_monto)
        btnCrearUsuario = findViewById(R.id.confirmar)

        btnCrearUsuario.setOnClickListener {

            if(validateInput()) {

                val fecha: LocalDate = LocalDate.now()
                val nuevo = User("","","","","",0.0,fecha.toString())
                val usuario = usuario.text.toString()
                val password = password.text.toString()
                val confirmarPassword = confirmPw.text.toString()
                val nombre = nombre.text.toString()
                val apellido = apellido.text.toString()
                val money = money.text

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
