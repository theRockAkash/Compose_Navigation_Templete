package com.solutions.app.retrofit

import com.solutions.app.modules.auth.data.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @Created by akash on 23/11/2024.
 * Know more about author on https://akash.cloudemy.in
 */
interface Apis {


    @POST("App/Login")
    suspend fun login(
        @Body body: LoginRequest
    ): Response<String>



}