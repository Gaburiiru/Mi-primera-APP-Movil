package Repository

import Entidades.User
import android.os.Build
import androidx.annotation.RequiresApi

object UserRepository {

    val users = mutableListOf<User>()
    val session = mutableListOf<User>()

    init {
        users.add(User("1504L", "BRIAN_BAYARRI", "abc123", "Brian", "Bayarri", 350.50, "2022/12/10"))
        users.add(User("2802L", "AHOZ","lock_password", "Aylen", "Hoz", 200.50, "2021/01/11"))
        users.add(User("1510L", "DIEGOTE", "@12345", "Diego", "Gonzales", 12.0, "2018/04/15"))
    }

    fun existe(nickname: String,password: String): Boolean {
        return (users.any { usuario: User -> usuario.nickName.equals(nickname) && usuario.password.equals(password)});
    }

    fun agregar(usuario: User) {
        users.add(usuario)
    }

    fun iniciar(nickname: String, password: String): User {
        var usuarioIniciado = User()
        for (elemento in users) {
            if (elemento.nickName.equals(nickname) && elemento.password.equals(password)) {
                usuarioIniciado = elemento
                session.add(elemento)
            }
        }
        return usuarioIniciado
    }
}