package com.example.asset.todoproject.activities.main

import com.example.asset.todoproject.activities.common.BasePresenter
import com.example.asset.todoproject.activities.common.BaseView
import com.example.asset.todoproject.models.Todo

interface AppContract {

    interface View: BaseView<Presenter>{
        fun onGetDataSuccess(list: ArrayList<Todo>)
        fun onDeleteSuccess()
        fun onError(msg: String)
    }

    interface Presenter: BasePresenter<View>{
        fun getData(userId: String)
        fun delete(userId: String, id: Int)
    }
}