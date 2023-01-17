package com.example.finalurilast.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import androidx.navigation.fragment.findNavController
import com.example.finalurilast.R
import com.google.firebase.auth.FirebaseAuth

class Login:Fragment(R.layout.login_layout) {
    private lateinit var logBut: Button
    private lateinit var forgBut:Button
    private lateinit var creBut:Button
    private lateinit var logEmail:EditText
    private lateinit var logPass:EditText
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logBut = view.findViewById(R.id.loginBut)
        forgBut = view.findViewById(R.id.forgotBut)
        creBut = view.findViewById(R.id.createBut)
        logEmail = view.findViewById(R.id.logEmail)
        logPass = view.findViewById(R.id.loginPass)

        logBut.setOnClickListener {
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(logEmail.text.toString(), logPass.text.toString())
                .addOnCompleteListener { task->
                    if(task.isSuccessful){
                        val signed = signedIn()
                        val fragmentManager = parentFragmentManager
                        fragmentManager.commitNow {
                            setReorderingAllowed(true)
                            replace(R.id.nav_host_fragment,signed)
                        }
                    }
                }

        }
        forgBut.setOnClickListener {
            val forgot = ForgotPass()
            val fragmentManager = parentFragmentManager
            fragmentManager.commitNow {
                setReorderingAllowed(true)
                replace(R.id.nav_host_fragment,forgot)
            }
        }
        creBut.setOnClickListener {
            val reg = Register()
            val fragmentManager = parentFragmentManager
            fragmentManager.commitNow {
                setReorderingAllowed(true)
                replace(R.id.nav_host_fragment,reg)
            }
        }
    }
}