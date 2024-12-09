package com.example.practicanavigationdrawer.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicanavigationdrawer.models.Restaurant
import com.example.practicanavigationdrawer.repositories.RestaurantRepository

class RestaurantDetailViewModel : ViewModel() {

    private val _restaurant = MutableLiveData<Restaurant>()
    val restaurant: LiveData<Restaurant> get() = _restaurant

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchRestaurantDetails(restaurantId: Int) {
        RestaurantRepository.getRestaurantDetails(
            restaurantId = restaurantId,
            onSuccess = { fetchedRestaurant ->
                _restaurant.postValue(fetchedRestaurant)
            },
            onError = { throwable ->
                _error.postValue(throwable.message)
            }
        )
    }
}
