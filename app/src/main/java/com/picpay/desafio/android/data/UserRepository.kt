package com.picpay.desafio.android.data

import androidx.room.withTransaction
import com.picpay.desafio.android.api.UsersApi
import com.picpay.desafio.android.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class UserRepository @Inject constructor(

    private val api: UsersApi,
    private val db: UserDatabase
) {
    private val userDao = db.userDao()

    fun getUsers() = networkBoundResource(
        query = {
            userDao.getAllUsers()
        },
        fetch = {
            delay(2000)
            api.getUsers()
        },
        saveFetchResult = { users ->
            db.withTransaction {
                userDao.deleteAllUsers()
                userDao.insertUsers(users)
            }
        }
    )
}