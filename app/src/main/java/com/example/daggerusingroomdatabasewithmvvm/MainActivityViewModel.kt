package com.example.daggerusingroomdatabasewithmvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.daggerusingroomdatabasewithmvvm.database.UserDao
import com.example.daggerusingroomdatabasewithmvvm.database.UserEntity
import javax.inject.Inject

class MainActivityViewModel(application: Application): AndroidViewModel(application){

    @Inject
    lateinit var userDao: UserDao

    private val _allUserList: MutableLiveData<List<UserEntity>> = MutableLiveData()
    val allUserList : LiveData<List<UserEntity>> get() = _allUserList

    init {
        (application as MyApp).getAppComponent().inject(this)
        getAllRecords()
    }


    fun getRecordsObserver(): LiveData<List<UserEntity>> {
        return allUserList
    }

    fun getAllRecords() {
        val list = userDao.getAllRecordsFromDB()
        _allUserList.postValue(list)
    }

    fun insertRecord(userEntity: UserEntity) {
        userDao.insertRecord(userEntity)
        getAllRecords()
    }
}