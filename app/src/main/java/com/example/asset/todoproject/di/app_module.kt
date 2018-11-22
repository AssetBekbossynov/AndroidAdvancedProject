package com.example.asset.todoproject.di

import com.example.asset.todoproject.activities.registration.RegistrationContract
import com.example.asset.todoproject.activities.registration.RegistrationPresenter
import org.koin.dsl.module.module

val appModule = module {

    factory { (view: RegistrationContract.View) -> RegistrationPresenter(view) as RegistrationContract.Presenter }

}

val todoApp = listOf(appModule)
