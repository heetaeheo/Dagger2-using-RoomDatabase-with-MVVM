package com.example.daggerusingroomdatabasewithmvvm.di

import com.example.daggerusingroomdatabasewithmvvm.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(mainActivityViewModel: MainActivityViewModel)
}