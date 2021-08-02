package com.picpay.desafio.android.api

import com.picpay.desafio.android.model.User
import retrofit2.Response
import retrofit2.http.GET

interface UsersApi {

    companion object {
        const val BASE_URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
    }

    @GET("users")
    suspend fun getUsers(): List<User>

}