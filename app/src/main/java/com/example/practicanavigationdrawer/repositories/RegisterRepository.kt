package com.example.practicanavigationdrawer.repositories

import com.example.practicanavigationdrawer.api.ApiService
import com.example.practicanavigationdrawer.models.Register
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RegisterRepository {

    private val apiService: ApiService by lazy {
        RetrofitRepository.createService(ApiService::class.java)
    }


    fun registerUser(
        name: String,
        email: String,
        password: String,
        role: Int,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val request = Register(name, email, password, role)

        apiService.registerUser(request).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    onError("Error en el registro: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                onError(t.message ?: "Error desconocido")
            }
        })
    }
}