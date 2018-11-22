package com.example.asset.todoproject.activities.registration

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.asset.todoproject.R
import com.example.asset.todoproject.helpers.Logger
import com.example.asset.todoproject.models.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import kotlinx.android.synthetic.main.toolbar.title as activityTitle

class RegistrationActivity : AppCompatActivity(), RegistrationContract.View {
    override val presenter: RegistrationContract.Presenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        activityTitle.text = "Registration"

        backbutton.setOnClickListener {
            finish()
        }

        register.setOnClickListener {
            val user = User(username.editText!!.text.toString(),
                    password.editText!!.text.toString(),
                    email.editText!!.text.toString(), null)
            presenter.register(user)
        }
    }

    override fun onCreateSuccess() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun onCreateError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
