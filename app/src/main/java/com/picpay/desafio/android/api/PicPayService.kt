package com.picpay.desafio.android.api

import com.picpay.desafio.android.model.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PicPayService {

    suspend fun getUsers(): Response<List<User>> = api.getUsers()

    private val api: UsersApi= Retrofit.Builder()
        .baseUrl("https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UsersApi::class.java)

}