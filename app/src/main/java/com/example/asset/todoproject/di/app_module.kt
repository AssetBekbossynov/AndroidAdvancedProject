package com.example.asset.todoproject.di

import com.example.asset.todoproject.activities.add.AddTodoContract
import com.example.asset.todoproject.activities.add.AddTodoPresenter
import com.example.asset.todoproject.activities.auth.MainContract
import com.example.asset.todoproject.activities.auth.MainPresenter
import com.example.asset.todoproject.activities.main.AppContract
import com.example.asset.todoproject.activities.main.AppPresenter
import com.example.asset.todoproject.activities.registration.RegistrationContract
import com.example.asset.todoproject.activities.registration.RegistrationPresenter
import org.koin.dsl.module.module

val appModule = module {

    factory { (view: RegistrationContract.View) -> RegistrationPresenter(view) as RegistrationContract.Presenter }
    factory { (view: MainContract.View) -> MainPresenter(view) as MainContract.Presenter }
    factory { (view: AddTodoContract.View) -> AddTodoPresenter(view) as AddTodoContract.Presenter }
    factory { (view: AppContract.View) -> AppPresenter(view) as AppContract.Presenter }

}

val todoApp = listOf(appModule)
