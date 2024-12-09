package com.example.practicanavigationdrawer.repositories

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitRepository {

    private const val BASE_URL = "http://proyectodelivery.jmacboy.com/api/"
    private var authToken: String = "" // Token dinámico

    fun setAuthToken(token: String) {
        authToken = token
    }

    fun getAuthToken(): String {
        return authToken
    }

    private val headerInterceptor = Interceptor { chain ->
        val requestBuilder = chain.request().newBuilder()
            .addHeader("Accept", "application/json")
        if (authToken.isNotEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer $authToken")
        }
        chain.proceed(requestBuilder.build())
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor) // Agregar encabezados dinámicos
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create()) // Convertir JSON automáticamente
        .build()

    fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }
}
