package com.example.superheroapi.data

import com.example.superheroapi.data.response.SuperHeroResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperHeroApiService {

    @GET("/api/659b759965510ad89c40a2ddaec5a7af/search/{name}")
    suspend fun getSuperHeroes(@Path ("name") superHeroData:String): Response<SuperHeroResponse>
}