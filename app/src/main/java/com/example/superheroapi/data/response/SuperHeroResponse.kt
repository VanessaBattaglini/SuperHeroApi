package com.example.superheroapi.data.response

import com.google.gson.annotations.SerializedName

data class SuperHeroResponse(
    @SerializedName("response") val response: String
)