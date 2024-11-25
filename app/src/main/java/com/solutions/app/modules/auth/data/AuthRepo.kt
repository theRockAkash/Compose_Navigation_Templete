package com.solutions.app.modules.auth.data

import com.solutions.app.retrofit.Apis
import com.solutions.app.util.NetworkManager
import javax.inject.Inject

/**
 * @Created by akash on 23-11-2024.
 * Know more about author at https://akash.cloudemy.in
 */
class AuthRepo @Inject constructor(
    private val networkManager: NetworkManager,
    private val apis: Apis
) {

    suspend fun login(body: LoginRequest) {
        apis.login(body)
    }
}