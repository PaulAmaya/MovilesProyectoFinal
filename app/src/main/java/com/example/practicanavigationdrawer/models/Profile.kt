package com.example.practicanavigationdrawer.models

import com.google.gson.annotations.SerializedName

data class Profile(
    @SerializedName("id") val id: Int,
    @SerializedName("role") val role: Int
)