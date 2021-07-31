package com.picpay.desafio.android.ui

import androidx.lifecycle.*
import com.picpay.desafio.android.api.PicPayService
import com.picpay.desafio.android.data.AppDatabase
import com.picpay.desafio.android.model.User
import kotlinx.coroutines.*

class MainViewModel : ViewModel(){

    private val service = PicPayService()
    private val _users = MutableLiveData<List<User>>()

    val users: LiveData<List<User>>
        get() = _users

    fun fetchUsers(){
        viewModelScope.launch(Dispatchers.IO){
            val res = service.getUsers()
            if(res.isSuccessful){
                withContext(Dispatchers.Main){
                    _users.value = res.body()
                }
            }
        }
    }

}