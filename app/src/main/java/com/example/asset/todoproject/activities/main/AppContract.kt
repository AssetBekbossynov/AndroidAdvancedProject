package com.example.asset.todoproject.activities.main

import com.example.asset.todoproject.activities.common.BasePresenter
import com.example.asset.todoproject.activities.common.BaseView

interface AppContract {

    interface View: BaseView<Presenter>{
        fun onGetDataSuccess()
        fun onGetDataError()
        fun onDeleteSuccess()
        fun onEditSuccess()
        fun onError(msg: String)
    }

    interface Presenter: BasePresenter<View>{
        fun getData(userId: String)
        fun delete(id: String)
        fun edit(id: String)
    }
}