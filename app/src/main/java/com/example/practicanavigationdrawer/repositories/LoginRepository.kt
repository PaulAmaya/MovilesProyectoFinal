package com.example.practicanavigationdrawer.repositories

import android.content.Context

import com.example.practicanavigationdrawer.api.ApiService
import com.example.practicanavigationdrawer.models.LoginRequest
import com.example.practicanavigationdrawer.models.LoginResponse
import com.example.practicanavigationdrawer.models.UserResponse
import com.example.practicanavigationdrawer.utils.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object LoginRepository {

    fun login(
        context: Context,
        loginRequest: LoginRequest,
        onSuccess: (LoginResponse) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val service = RetrofitRepository.createService(ApiService::class.java)
        service.login(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        val sessionManager = SessionManager(context)
                        sessionManager.saveAuthToken(it.access_token)

                        RetrofitRepository.setAuthToken(it.access_token)

                        onSuccess(it)
                    }
                } else {
                    onError(Throwable("Error en la respuesta: ${response.errorBody()?.string()}"))
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                onError(t)
            }
        })
    }

    fun getUserDetails(
        onSuccess: (UserResponse) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val service = RetrofitRepository.createService(ApiService::class.java)
        service.getUserDetails().enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) }
                } else {
                    onError(Throwable("Error en la respuesta: ${response.errorBody()?.string()}"))
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                onError(t)
            }
        })
    }
}
