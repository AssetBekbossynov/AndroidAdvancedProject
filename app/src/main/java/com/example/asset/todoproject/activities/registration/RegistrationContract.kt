package com.example.asset.todoproject.activities.registration

import com.example.asset.todoproject.activities.common.BasePresenter
import com.example.asset.todoproject.activities.common.BaseView
import com.example.asset.todoproject.models.User

interface RegistrationContract {

    interface View: BaseView<Presenter>{
        fun onCreateSuccess()
        fun onCreateError(msg: String)
    }

    interface Presenter: BasePresenter<View>{
        fun register(user: User)
    }
}