package com.example.asset.todoproject.activities.auth

import com.google.firebase.auth.FirebaseAuth

class MainPresenter(override var view: MainContract.View?) :
    MainContract.Presenter {

    val auth = FirebaseAuth.getInstance()

    override fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful){
                val currentUser = auth.currentUser
                if (currentUser != null)
                    view?.onLogSuccess(currentUser)
                else
                    view?.onLogError(it.exception?.message!!)
            }else{
                view?.onLogError(it.exception?.message!!)
            }
        }
    }
}