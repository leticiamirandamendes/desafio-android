package com.picpay.desafio.android

import com.picpay.desafio.android.api.PicPayService
import com.picpay.desafio.android.api.UsersApi
import com.picpay.desafio.android.model.User

class ExampleService(
    private val service: UsersApi
) {

    suspend fun example(): List<User> {
        val users = service.getUsers()
        return users ?: emptyList()
    }
}