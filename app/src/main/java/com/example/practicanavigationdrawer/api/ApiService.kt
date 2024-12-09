package com.example.practicanavigationdrawer.api


import com.example.practicanavigationdrawer.models.LoginRequest
import com.example.practicanavigationdrawer.models.LoginResponse
import com.example.practicanavigationdrawer.models.Order
import com.example.practicanavigationdrawer.models.OrderRequest
import com.example.practicanavigationdrawer.models.Product
import com.example.practicanavigationdrawer.models.Register
import com.example.practicanavigationdrawer.models.Restaurant
import com.example.practicanavigationdrawer.models.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("users/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @GET("orders/free")
    fun getPendingOrders(): Call<List<Order>>

    @GET("me")
    fun getUserDetails(): Call<UserResponse>

    @GET("restaurants")
    fun getRestaurants(): Call<List<Restaurant>>

    @GET("restaurants/{id}")
    fun getRestaurantDetails(
        @Path("id") restaurantId: Int
    ): Call<Restaurant>

    @GET("products/{id}")
    fun getProductDetails(
        @Path("id") productId: Int
    ): Call<Product>

    @POST("orders")
    fun placeOrder(@Body orderRequest: OrderRequest): Call<Void>

    @GET("orders")
    fun getOrders(): Call<List<Order>>

    @POST("users")
    fun registerUser(@Body register: Register): Call<Void>

}