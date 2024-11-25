package com.solutions.app.retrofit

/**
 * @Created by akash on 23/11/2024.
 * Know more about author on https://akash.cloudemy.in
 */
sealed class UiState<T>(val data: T? = null, val msg: String = "") {

    class Success<T>(data: T) : UiState<T>(data = data)
    class Error<T>(msg: String) : UiState<T>(msg = msg)
    class Loading<T>() : UiState<T>()
}