package com.example.practicanavigationdrawer.repositories



import com.example.practicanavigationdrawer.api.ApiService
import com.example.practicanavigationdrawer.models.Restaurant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RestaurantRepository {

    private val service = RetrofitRepository.createService(ApiService::class.java)

    fun getRestaurantList(
        onSuccess: (List<Restaurant>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        service.getRestaurants().enqueue(object : Callback<List<Restaurant>> {
            override fun onResponse(
                call: Call<List<Restaurant>>,
                response: Response<List<Restaurant>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) }
                } else {
                    val errorBody = response.errorBody()?.string()
                    onError(Throwable("Error en la respuesta: $errorBody"))
                }
            }

            override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                onError(t)
            }
        })
    }

    fun getRestaurantDetails(
        restaurantId: Int,
        onSuccess: (Restaurant) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        service.getRestaurantDetails(restaurantId).enqueue(object : Callback<Restaurant> {
            override fun onResponse(call: Call<Restaurant>, response: Response<Restaurant>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) }
                } else {
                    val errorBody = response.errorBody()?.string()
                    onError(Throwable("Error en la respuesta: $errorBody"))
                }
            }

            override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                onError(t)
            }
        })
    }

}
