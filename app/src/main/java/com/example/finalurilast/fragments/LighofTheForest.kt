package com.example.finalurilast.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import com.example.finalurilast.R

class LighofTheForest:Fragment(R.layout.lightofthe_layout) {
    private lateinit var back:Button
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back = view.findViewById(R.id.lightBack)
        back.setOnClickListener {
            val main = signedIn()
            val fragmentManager = parentFragmentManager
            fragmentManager.commitNow {
                setReorderingAllowed(true)
                replace(R.id.nav_host_fragment_sign, main)
            }
        }
    }
}