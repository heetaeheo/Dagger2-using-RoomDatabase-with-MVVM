package com.example.daggerusingroomdatabasewithmvvm.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class] , version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUserDao() : UserDao

    companion object{
        const val DB_NAME = "app_db"
    }
}