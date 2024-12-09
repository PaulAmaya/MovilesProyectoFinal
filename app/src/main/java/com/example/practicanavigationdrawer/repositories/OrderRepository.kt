package com.example.practicanavigationdrawer.repositories


import com.example.practicanavigationdrawer.api.ApiService
import com.example.practicanavigationdrawer.models.Order
import com.example.practicanavigationdrawer.models.OrderRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object OrderRepository {

    private val service = RetrofitRepository.createService(ApiService::class.java)

    fun getOrdersList(onSuccess: (List<Order>) -> Unit, onError: (Throwable) -> Unit) {
        val token = RetrofitRepository.getAuthToken()
        if (token.isEmpty()) {
            onError(Throwable("Token no configurado. Por favor, inicie sesión nuevamente."))
            return
        }

        service.getPendingOrders().enqueue(object : Callback<List<Order>> {
            override fun onResponse(call: Call<List<Order>>, response: Response<List<Order>>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) }
                } else {
                    val errorBody = response.errorBody()?.string()
                    onError(Throwable("Error en la respuesta: $errorBody"))
                }
            }

            override fun onFailure(call: Call<List<Order>>, t: Throwable) {
                onError(t)
            }
        })
    }

    fun placeOrder(
        orderRequest: OrderRequest,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        service.placeOrder(orderRequest).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    val errorBody = response.errorBody()?.string()
                    onError(Throwable("Error en la respuesta: $errorBody"))
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                onError(t)
            }
        })
    }

    fun getUserOrders(
        onSuccess: (List<Order>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        service.getOrders().enqueue(object : Callback<List<Order>> {
            override fun onResponse(call: Call<List<Order>>, response: Response<List<Order>>) {
                if (response.isSuccessful) {
                    response.body()?.let(onSuccess)
                } else {
                    val errorBody = response.errorBody()?.string()
                    onError(Throwable("Error en la respuesta: $errorBody"))
                }
            }

            override fun onFailure(call: Call<List<Order>>, t: Throwable) {
                onError(t)
            }
        })
    }

}
