package com.example.superheroapi.data.response

import com.google.gson.annotations.SerializedName

data class SuperHeroResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val results: List<SuperHeroItemResult>
)

data class SuperHeroItemResult(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String

)