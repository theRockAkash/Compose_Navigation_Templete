package com.solutions.app.modules.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solutions.app.modules.auth.data.AuthRepo
import com.solutions.app.modules.auth.data.LoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Created by akash on 23-11-2024.
 * Know more about author at https://akash.cloudemy.in
 */
@HiltViewModel
class LoginViewModel @Inject constructor(private val repo: AuthRepo) : ViewModel() {
    fun login(email: String, password: String){
        viewModelScope.launch {
            repo.login(LoginRequest(email, password))
        }
    }
}