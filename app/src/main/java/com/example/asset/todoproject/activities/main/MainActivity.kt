package com.example.asset.todoproject.activities.main

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.asset.todoproject.R
import com.example.asset.todoproject.activities.registration.RegistrationActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val REGISTRATION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        register.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivityForResult(intent, REGISTRATION)
        }

        signIn.setOnClickListener {

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REGISTRATION && resultCode == Activity.RESULT_OK){
            Toast.makeText(this, "Registration was made successfully. Now you can login with your email and password", Toast.LENGTH_SHORT).show()
        }else if (requestCode == REGISTRATION && resultCode == Activity.RESULT_CANCELED){
            Toast.makeText(this, "Something went wrong. Try again", Toast.LENGTH_SHORT).show()
        }
    }
}
