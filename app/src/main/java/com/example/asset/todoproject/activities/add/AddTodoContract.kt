package com.example.asset.todoproject.activities.add

import com.example.asset.todoproject.activities.common.BasePresenter
import com.example.asset.todoproject.activities.common.BaseView
import com.example.asset.todoproject.models.Todo

interface AddTodoContract {

    interface View: BaseView<Presenter>{
        fun onAddSuccess()
        fun onAddError(msg: String)
    }

    interface Presenter: BasePresenter<View>{
        fun add(todo: Todo)
    }
}