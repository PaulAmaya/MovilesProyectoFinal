package com.example.practicanavigationdrawer.ui.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicanavigationdrawer.models.LoginRequest
import com.example.practicanavigationdrawer.models.LoginResponse
import com.example.practicanavigationdrawer.models.UserResponse
import com.example.practicanavigationdrawer.repositories.LoginRepository


class LoginViewModel : ViewModel() {

    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> get() = _loginResponse

    private val _userResponse = MutableLiveData<UserResponse>()
    val userResponse: LiveData<UserResponse> get() = _userResponse

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error


    fun login(context: Context, email: String, password: String) {
        val loginRequest = LoginRequest(email, password)

        // Asegúrate de que los argumentos estén en el orden correcto
        LoginRepository.login(
            context = context, // Primer argumento: Context
            loginRequest = loginRequest, // Segundo argumento: LoginRequest
            onSuccess = { response ->
                _loginResponse.postValue(response) // Publica el resultado exitoso
            },
            onError = { throwable ->
                _error.postValue(throwable.message) // Publica el mensaje de error
            }
        )
    }

    fun getUserDetails() {
        LoginRepository.getUserDetails(
            onSuccess = { user ->
                _userResponse.postValue(user)
            },
            onError = { throwable ->
                _error.postValue(throwable.message)
            }
        )
    }
}
