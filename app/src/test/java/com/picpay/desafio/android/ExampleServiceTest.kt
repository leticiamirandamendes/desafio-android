package com.picpay.desafio.android

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.api.PicPayService
import com.picpay.desafio.android.api.UsersApi
import com.picpay.desafio.android.model.User
import junit.framework.Assert.assertEquals
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

class ExampleServiceTest {

    private val api = mock<UsersApi>()

    private val service = ExampleService(api)

    @Test
    suspend fun exampleTest() {
        // given
        val call = mock<Call<List<User>>>()
        val resultCall = mock<List<User>>()
        val expectedUsers = emptyList<User>()

        whenever(call.execute()).thenReturn(Response.success(expectedUsers))
        whenever(api.getUsers()).thenReturn(resultCall)

        // when
        val users = service.example()

        // then
        assertEquals(users, expectedUsers)
    }
}