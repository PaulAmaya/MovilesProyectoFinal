package com.example.practicanavigationdrawer.repositories


import com.example.practicanavigationdrawer.api.ApiService
import com.example.practicanavigationdrawer.models.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ProductRepository {

    private val service = RetrofitRepository.createService(ApiService::class.java)

    fun getProductDetails(
        productId: Int,
        onSuccess: (Product) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        service.getProductDetails(productId).enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) }
                } else {
                    val errorBody = response.errorBody()?.string()
                    onError(Throwable("Error en la respuesta: $errorBody"))
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                onError(t)
            }
        })
    }
}
