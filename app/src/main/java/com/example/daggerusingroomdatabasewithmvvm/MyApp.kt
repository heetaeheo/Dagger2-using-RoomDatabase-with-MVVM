package com.example.daggerusingroomdatabasewithmvvm

import android.app.Application
import com.example.daggerusingroomdatabasewithmvvm.di.AppComponent
import com.example.daggerusingroomdatabasewithmvvm.di.AppModule
import com.example.daggerusingroomdatabasewithmvvm.di.DaggerAppComponent


class MyApp : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun getAppComponent() : AppComponent{
        return appComponent
    }


}