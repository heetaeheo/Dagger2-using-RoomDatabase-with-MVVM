package com.example.daggerusingroomdatabasewithmvvm

import android.app.Application
import com.example.daggerusingroomdatabasewithmvvm.di.AppComponent
import com.example.daggerusingroomdatabasewithmvvm.di.AppModule
import com.example.daggerusingroomdatabasewithmvvm.di.DaggerAppComponent


class MyApp : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}