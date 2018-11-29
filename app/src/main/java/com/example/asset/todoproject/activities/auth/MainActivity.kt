package com.example.asset.todoproject.activities.auth

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.asset.todoproject.R
import com.example.asset.todoproject.activities.main.AppActivity
import com.example.asset.todoproject.activities.registration.RegistrationActivity
import com.example.asset.todoproject.helpers.Logger
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), MainContract.View {
    override val presenter: MainContract.Presenter by inject { parametersOf(this) }

    private val REGISTRATION = 1

    var currentUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        register.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivityForResult(intent, REGISTRATION)
        }

        signIn.setOnClickListener {
            presenter.login(username.editText!!.text.toString(), password.editText!!.text.toString())
        }
    }

    override fun onLogSuccess(user: FirebaseUser) {
        currentUser = user
        val intent = Intent(this, AppActivity::class.java)
        intent.putExtra("userId", user.uid)
        startActivity(intent)
        finish()
    }

    override fun onLogError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
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
