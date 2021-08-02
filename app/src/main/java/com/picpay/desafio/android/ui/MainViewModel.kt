package com.picpay.desafio.android.ui

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.picpay.desafio.android.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    repository: UserRepository
) : ViewModel() {
    var listState: Parcelable? = null
    val users = repository.getUsers().asLiveData()

}
