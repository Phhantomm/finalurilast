package com.example.finalurilast.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.finalurilast.R
import com.example.finalurilast.data.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TicketPage:Fragment(R.layout.tickets_layout) {
    private lateinit var notetext:EditText
    private lateinit var add:Button
    private lateinit var text:TextView
    private lateinit var sharedPreferences:SharedPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notetext = view.findViewById(R.id.noteText)
        add = view.findViewById(R.id.buttonAdd)
        text = view.findViewById(R.id.noteView)
        sharedPreferences = requireActivity().getSharedPreferences("Films", Context.MODE_PRIVATE)
        val notes = sharedPreferences.getString("NOTES","")
        text.text = notes
        add.setOnClickListener {
            val note = notetext.text.toString()
            val textview = text.text.toString()
            val result = textview + "\n" + note
            text.text = result
            notetext.setText("")
            sharedPreferences.edit()
                .putString("NOTES",result)
                .apply()
        }

    }
}