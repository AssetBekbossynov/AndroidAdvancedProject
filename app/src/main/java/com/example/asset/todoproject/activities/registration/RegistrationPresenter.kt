package com.example.asset.todoproject.activities.registration

import com.example.asset.todoproject.helpers.Logger
import com.example.asset.todoproject.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RegistrationPresenter(override var view: RegistrationContract.View?) : RegistrationContract.Presenter{

    val auth = FirebaseAuth.getInstance()

    override fun register(user: User) {
        auth.createUserWithEmailAndPassword(user.email, user.password).addOnCompleteListener {
            if (it.isSuccessful){
                view?.onCreateSuccess()
            }else{
                Logger.msg("here " + it.exception?.message)
                view?.onCreateError(it.exception?.message!!)
            }
        }
    }
}