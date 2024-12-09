package com.example.practicanavigationdrawer.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicanavigationdrawer.models.Order
import com.example.practicanavigationdrawer.repositories.OrderRepository


class OrdersViewModel : ViewModel() {

    private val _orders = MutableLiveData<List<Order>>()
    val orders: LiveData<List<Order>> get() = _orders

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchOrders() {
        OrderRepository.getOrdersList(
            onSuccess = { fetchedOrders ->
                _orders.postValue(fetchedOrders)
            },
            onError = { throwable ->
                _error.postValue(throwable.message)
            }
        )
    }
}
