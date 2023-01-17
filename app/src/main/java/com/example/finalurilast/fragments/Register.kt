package com.example.finalurilast.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import com.example.finalurilast.R
import com.example.finalurilast.data.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Register:Fragment(R.layout.register_layout) {
    private lateinit var create:Button
    private lateinit var name:EditText
    private lateinit var surname:EditText
    private lateinit var pass:EditText
    private lateinit var repass:EditText
    private lateinit var email:EditText
    private lateinit var back:Button
    private val db = FirebaseDatabase.getInstance().getReference("Users")
    private val auth = FirebaseAuth.getInstance()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        create = view.findViewById(R.id.register)
        name = view.findViewById(R.id.Name)
        surname = view.findViewById(R.id.Surname)
        email = view.findViewById(R.id.emailReg)
        pass = view.findViewById(R.id.regPass)
        repass = view.findViewById(R.id.repregPass)
        back = view.findViewById(R.id.backReg)

        back.setOnClickListener {
            val login = Login()
            val fragmentManager = parentFragmentManager
            fragmentManager.commitNow {
                setReorderingAllowed(true)
                replace(R.id.nav_host_fragment,login)
            }
        }

        create.setOnClickListener {
            if (name.text.toString().isNotEmpty() && surname.text.toString()
                    .isNotEmpty() && email.text.toString().isNotEmpty() && pass.text.toString()
                    .isNotEmpty() && repass.text.toString().isNotEmpty()
            ) {
                if (repass.text.toString() == pass.text.toString()) {
                    FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(email.text.toString(), pass.text.toString())
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val main = signedIn()
                                val fragmentManager = parentFragmentManager
                                fragmentManager.commitNow {
                                    setReorderingAllowed(true)
                                    replace(R.id.nav_host_fragment, main)
                                }
                                val user = Users(
                                    name.text.toString(),
                                    surname.text.toString(),
                                    email.text.toString()
                                )
                                val database = FirebaseDatabase.getInstance().getReference("Users")
                                database.push().setValue(user)
                            }

                        }
                }

            }
            else {
                Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
            }
        }



    }
}