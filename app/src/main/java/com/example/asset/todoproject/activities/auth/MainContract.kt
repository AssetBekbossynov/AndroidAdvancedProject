package com.example.asset.todoproject.activities.auth

import com.example.asset.todoproject.activities.common.BasePresenter
import com.example.asset.todoproject.activities.common.BaseView
import com.google.firebase.auth.FirebaseUser

interface MainContract {
    interface View: BaseView<Presenter>{
        fun onLogSuccess(user: FirebaseUser)
        fun onLogError(msg: String)
    }

    interface Presenter: BasePresenter<View>{
        fun login(email: String, password: String)
    }
}