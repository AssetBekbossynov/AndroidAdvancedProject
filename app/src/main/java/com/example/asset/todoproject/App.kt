package com.example.asset.todoproject

import android.support.multidex.MultiDexApplication
import com.example.asset.todoproject.di.todoApp
import org.koin.android.ext.android.startKoin

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin(this, todoApp)
    }

    companion object {
        @JvmStatic var instance: App? = null
            private set
    }
}
