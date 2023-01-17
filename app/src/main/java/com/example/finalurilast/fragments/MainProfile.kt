package com.example.finalurilast.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import com.example.finalurilast.R
import com.example.finalurilast.data.Reports
import com.example.finalurilast.data.Tickets
import com.example.finalurilast.data.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.math.sign

class MainProfile:Fragment(R.layout.mainprof) {
    private lateinit var report:Button
    private lateinit var repBut:Button
    private lateinit var repCan:Button
    private lateinit var repText:EditText
    private lateinit var newPass:EditText
    private lateinit var signout:Button
    private lateinit var changepas:Button
    private lateinit var passwordchange:Button
    private lateinit var cancelpas:Button
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        report = view.findViewById(R.id.report)
        signout = view.findViewById(R.id.signoutBut)
        changepas = view.findViewById(R.id.changePass)

        signout.setOnClickListener {
            val login = Login()
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.commitNow{
                setReorderingAllowed(true)
                replace(R.id.nav_host_fragment,login)
            }
        }


        changepas.setOnClickListener {
            changedialog()
        }

        report.setOnClickListener {
            dialog()

        }
    }



    private fun dialog() {
        val dialogView = LayoutInflater.from(this.context).inflate(R.layout.report_dialog, null);
        val mBuilder = AlertDialog.Builder(this.context)
            .setView(dialogView)
            .setTitle("Report Form")
        val mAlertDialog = mBuilder.show()


        repBut = dialogView.findViewById(R.id.reportBut)
        repCan = dialogView.findViewById(R.id.reportCancel)
        repText = dialogView.findViewById(R.id.reportText)

        repBut.setOnClickListener {
            val reported = Reports(repText.text.toString())
            val database = FirebaseDatabase.getInstance().getReference("Reports")
            if (repText.text.toString().isEmpty()) {
                Toast.makeText(activity, "Invalid", Toast.LENGTH_SHORT).show()
            } else {
                database.child("Problem").push().setValue(reported)
                Toast.makeText(
                    activity,
                    "Thanks For Letting us Know The Problem",
                    Toast.LENGTH_SHORT
                )
                    .show()

                mAlertDialog.dismiss()
            }


        }

        repCan.setOnClickListener {

            mAlertDialog.dismiss()
        }
    }
    private fun changedialog() {
        val dialogView = LayoutInflater.from(this.context).inflate(R.layout.change_dialog, null);
        val mBuilder = AlertDialog.Builder(this.context)
            .setView(dialogView)
            .setTitle("Change Password")
        val mAlertDialog = mBuilder.show()


        passwordchange = dialogView.findViewById(R.id.changepassBut)
        cancelpas = dialogView.findViewById(R.id.changeCancel)
        newPass = dialogView.findViewById(R.id.changepasswordText)

        passwordchange.setOnClickListener {
            val pass = newPass.text.toString()
            if(newPass.text.toString().isNotEmpty()){
                FirebaseAuth.getInstance()
                    .currentUser?.updatePassword(pass)
                    ?.addOnCompleteListener { task->
                        Toast.makeText(activity,"Password has been changed",Toast.LENGTH_SHORT).show()
                        mAlertDialog.dismiss()
                    }

            }


        }

        cancelpas.setOnClickListener {

            mAlertDialog.dismiss()
        }
    }

}