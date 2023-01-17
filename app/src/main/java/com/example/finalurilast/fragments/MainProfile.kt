package com.example.finalurilast.fragments

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.finalurilast.R
import com.example.finalurilast.data.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainProfile:Fragment(R.layout.mainprof) {
    private lateinit var user:TextView
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance().getReference("Users")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        user = view.findViewById(R.id.textView3)


        db.child(auth.currentUser?.uid!!).addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val personInfo = snapshot.getValue(Users::class.java)?:return
                user.text = personInfo.Email
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}