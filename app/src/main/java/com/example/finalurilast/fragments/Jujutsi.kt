package com.example.finalurilast.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import com.example.finalurilast.R

class Jujutsi:Fragment(R.layout.jujutsu_layout) {
    private lateinit var back: Button
    private lateinit var buy: Button
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back = view.findViewById(R.id.jujuBack)
        buy = view.findViewById(R.id.jujuTicket)
        back.setOnClickListener {
            val main = signedIn()
            val fragmentManager = parentFragmentManager
            fragmentManager.commitNow {
                setReorderingAllowed(true)
                replace(R.id.nav_host_fragment_sign, main)
            }
        }
        buy.setOnClickListener {

        }


    }

    private fun dialog() {
        val mDialogView = LayoutInflater.from(this.context).inflate(R.layout.dialog, null);
        val mBuilder = AlertDialog.Builder(this.context)
            .setView(mDialogView)
            .setTitle("Order Form")
        val mAlertDialog = mBuilder.show()


    }
}