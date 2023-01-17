package com.example.finalurilast.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import com.example.finalurilast.R
import com.google.firebase.auth.FirebaseAuth

class ForgotPass:Fragment(R.layout.forgot_layout) {
    private lateinit var reset:Button
    private lateinit var resetEmail:EditText
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reset = view.findViewById(R.id.button)
        resetEmail=view.findViewById(R.id.forgotEmail)
        reset.setOnClickListener {
            FirebaseAuth.getInstance().sendPasswordResetEmail(resetEmail.text.toString())
                .addOnCompleteListener { task->
                    if(task.isSuccessful){
                        Toast.makeText(activity,"Check Your E-mail",Toast.LENGTH_LONG).show()
                        val log = Login()
                        val fragmentManager = parentFragmentManager
                        fragmentManager.commitNow {
                            setReorderingAllowed(true)
                            replace(R.id.nav_host_fragment, log)
                        }

                    }
                    else{
                        Toast.makeText(activity,"Invalid E-mail",Toast.LENGTH_SHORT).show()
                    }

            }
        }
    }
}