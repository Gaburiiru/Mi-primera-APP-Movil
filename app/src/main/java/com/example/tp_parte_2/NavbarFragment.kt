import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.tp_parte_2.R

class NavbarFragment : Fragment() {

    private lateinit var btnInicio: Button
    private lateinit var btnBiblioteca: Button
    private lateinit var btnCompras: Button
    private lateinit var btnRecarga: Button

    interface OnBotonClickListener {
        fun onInicioClick()
        fun onBibliotecaClick()
        fun onComprasClick()
        fun onRecargaClick()
    }

    private var botonClickListener: OnBotonClickListener? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_navar, container, false)

        btnInicio = view.findViewById(R.id.inicio)
        btnBiblioteca = view.findViewById(R.id.biblioteca)
        btnCompras = view.findViewById(R.id.compras)
        btnRecarga = view.findViewById(R.id.recargar)

        btnInicio.setOnClickListener { botonClickListener?.onInicioClick() }
        btnBiblioteca.setOnClickListener { botonClickListener?.onBibliotecaClick() }
        btnCompras.setOnClickListener { botonClickListener?.onComprasClick() }
        btnRecarga.setOnClickListener { botonClickListener?.onRecargaClick() }

        return view
    }

    fun setOnBotonClickListener(listener: OnBotonClickListener) {
        botonClickListener = listener
    }

    companion object {
        fun newInstance(): NavbarFragment {
            return NavbarFragment()
        }
    }
}