package com.example.demo_kotlin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository): ViewModel() {

    fun login(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val jsonBody = "{ username: \"$username\"}"
            loginRepository.makeLoginRequest(jsonBody)
        }
    }
}