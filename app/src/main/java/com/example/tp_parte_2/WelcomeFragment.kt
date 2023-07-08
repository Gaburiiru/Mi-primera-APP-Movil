package com.example.tp_parte_2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WelcomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WelcomeFragment : Fragment() {

    private var nickName: String? = null
    private var createdDate: String? = null
    private var money: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            nickName = it.getString(NICKNAME_BUNDLE)
            createdDate = it.getString(CREATEDDATE_BUNDLE)
            money = it.getString(MONEY_BUNDLE)

            Log.i("prueba",nickName.orEmpty()
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    companion object {
      const val NICKNAME_BUNDLE= "user"
        const val CREATEDDATE_BUNDLE= "12/12/12"
       const val MONEY_BUNDLE= "1000"
        @JvmStatic
        fun newInstance(nickName: String, createdDate: String, money : Double) =
            WelcomeFragment().apply {
                arguments = Bundle().apply {
                    putString(NICKNAME_BUNDLE, nickName)
                    putString(CREATEDDATE_BUNDLE, createdDate)
                    putDouble(MONEY_BUNDLE,money)
                }
            }
    }
}