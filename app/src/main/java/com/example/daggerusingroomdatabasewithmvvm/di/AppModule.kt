package com.example.daggerusingroomdatabasewithmvvm.di

import android.app.Application
import androidx.room.Room
import com.example.daggerusingroomdatabasewithmvvm.database.AppDatabase
import com.example.daggerusingroomdatabasewithmvvm.database.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Singleton
    @Provides
    fun getUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.getUserDao()
    }

    @Singleton
    @Provides
    fun getRoomDbInstance(application: Application): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, AppDatabase.DB_NAME)
            .build()
    }

    @Provides
    fun provideApplication() = application
}