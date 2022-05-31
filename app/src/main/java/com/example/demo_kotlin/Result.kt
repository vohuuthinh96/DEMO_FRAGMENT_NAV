package com.example.demo_kotlin

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}
class LoginRepository() {
     suspend fun makeLoginRequest(jsonBody: String): Result<String> {
        // Move the execution of the coroutine to the I/O dispatcher
        return withContext(Dispatchers.IO) {
            // Blocking network request code
            return@withContext Result.Success("djt me may")
        }
    }
}