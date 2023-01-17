package com.example.finalurilast.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import com.example.finalurilast.R
import com.example.finalurilast.data.Tickets
import com.google.firebase.database.FirebaseDatabase

class Jokere:Fragment(R.layout.joker_layout) {
    private lateinit var back:Button
    private lateinit var yesButton: Button
    private lateinit var noButton: Button
    private lateinit var ticket: EditText
    private lateinit var filmName: EditText
    private lateinit var buy: Button
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back = view.findViewById(R.id.jokerBack)
        buy = view.findViewById(R.id.jokerBuy)
        back.setOnClickListener {
            val main = signedIn()
            val fragmentManager = parentFragmentManager
            fragmentManager.commitNow {
                setReorderingAllowed(true)
                replace(R.id.nav_host_fragment_sign, main)
            }
        }
        buy.setOnClickListener {
            dialog()
        }
    }
    private fun dialog() {
        val dialogView = LayoutInflater.from(this.context).inflate(R.layout.dialog, null);
        val mBuilder = AlertDialog.Builder(this.context)
            .setView(dialogView)
            .setTitle("Order Form")
        val mAlertDialog = mBuilder.show()


        yesButton = dialogView.findViewById(R.id.dialogYes)
        noButton = dialogView.findViewById(R.id.dialogNo)
        filmName = dialogView.findViewById(R.id.nameFilm)
        ticket = dialogView.findViewById(R.id.ticketAmount)

        yesButton.setOnClickListener {
            val ordered = Tickets(ticket.text.toString(),filmName.text.toString())
            val database = FirebaseDatabase.getInstance().getReference("Tickets")
            if(filmName.text.toString().isEmpty() || ticket.text.toString().isEmpty()){
                Toast.makeText(activity,"Invalid", Toast.LENGTH_SHORT).show()
            }
            else {
                database.child("Order").push().setValue(ordered)
                Toast.makeText(activity, "You have bought the ticket", Toast.LENGTH_SHORT).show()

                mAlertDialog.dismiss()
            }


        }

        noButton.setOnClickListener {

            mAlertDialog.dismiss()
        }
    }
}