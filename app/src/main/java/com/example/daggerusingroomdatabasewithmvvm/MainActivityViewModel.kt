package com.example.daggerusingroomdatabasewithmvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.daggerusingroomdatabasewithmvvm.database.UserDao
import com.example.daggerusingroomdatabasewithmvvm.database.UserEntity
import java.util.concurrent.locks.ReentrantLock
import javax.inject.Inject
import javax.inject.Provider
import kotlin.concurrent.withLock

class MainActivityViewModel @Inject constructor(
    private val userDao: UserDao
) : ViewModel() {

    private val _allUserList: MutableLiveData<List<UserEntity>> = MutableLiveData()
    val allUserList: LiveData<List<UserEntity>> get() = _allUserList

    private val lock = ReentrantLock()

    init {
        getAllRecords()
    }

    private fun getAllRecords() {
        Thread {
            lock.withLock {
                val list = userDao.getAllRecordsFromDB()
                _allUserList.postValue(list)
            }
        }.start()
    }

    fun insertRecord(userEntity: UserEntity) {
        Thread {
            lock.withLock { userDao.insertRecord(userEntity) }
            getAllRecords()
        }.start()
    }

    class Factory @Inject constructor(
        private val viewModelProvider: Provider<MainActivityViewModel>
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return viewModelProvider.get() as T
        }
    }
}