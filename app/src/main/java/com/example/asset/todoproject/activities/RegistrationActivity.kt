package com.example.asset.todoproject.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.asset.todoproject.R
import com.example.asset.todoproject.helpers.Logger
import com.example.asset.todoproject.models.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.title as activityTitle

class RegistrationActivity : AppCompatActivity() {

    val database = FirebaseDatabase.getInstance()
    var ref = database.getReference("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        activityTitle.text = "Registration"

        backbutton.setOnClickListener {
            finish()
        }

        register.setOnClickListener {
            Logger.msg("clicked")
            val user = User(username.editText!!.text.toString(),
                    password.editText!!.text.toString(),
                    email.editText!!.text.toString(), null)
            ref.child("users").addValueEventListener(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Logger.msg("here " + dataSnapshot.getValue(User::class.java))
                }
            })
        }
    }
}
