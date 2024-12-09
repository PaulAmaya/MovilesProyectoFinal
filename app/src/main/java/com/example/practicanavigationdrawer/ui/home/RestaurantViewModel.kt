package com.example.practicanavigationdrawer.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicanavigationdrawer.models.Restaurant
import com.example.practicanavigationdrawer.repositories.RestaurantRepository

class RestaurantViewModel : ViewModel() {

    private val _restaurants = MutableLiveData<List<Restaurant>>()
    val restaurants: LiveData<List<Restaurant>> get() = _restaurants

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchRestaurants() {
        RestaurantRepository.getRestaurantList(
            onSuccess = { fetchedRestaurants ->
                _restaurants.postValue(fetchedRestaurants)
            },
            onError = { throwable ->
                _error.postValue(throwable.message)
            }
        )
    }
}