package com.example.finalurilast.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import com.example.finalurilast.R

class SecretWars:Fragment(R.layout.secretwars_layout) {
    private lateinit var back:Button
    private lateinit var buy:Button
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back = view.findViewById(R.id.secretBack)
        buy = view.findViewById(R.id.secretBuy)
        back.setOnClickListener {
            val main = signedIn()
            val fragmentManager = parentFragmentManager
            fragmentManager.commitNow {
                setReorderingAllowed(true)
                replace(R.id.nav_host_fragment_sign,main)
            }
        }
        buy.setOnClickListener {
            Toast.makeText(activity,"Movie is not released yet! sight tight for further information!",Toast.LENGTH_SHORT).show()
        }
    }
}