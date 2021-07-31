package com.picpay.desafio.android.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.model.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO
}