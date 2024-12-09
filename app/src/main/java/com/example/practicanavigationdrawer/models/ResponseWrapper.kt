package com.example.practicanavigationdrawer.models

import com.google.gson.annotations.SerializedName

data class ResponseWrapper<T>(
    @SerializedName("data") val data: T
)
