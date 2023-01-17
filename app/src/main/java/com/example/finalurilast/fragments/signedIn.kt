package com.example.finalurilast.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.finalurilast.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class signedIn:Fragment(R.layout.signedin_frag){
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController:NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavigationView = view.findViewById(R.id.bottomnav)
        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment_sign) as NavHostFragment
        navController=navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)

    }
}