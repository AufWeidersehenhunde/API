package com.example.api

import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRaM {
    @GET("api/character")
    suspend fun getCharacters(@Query("page") page:Int):Response<CharacterList>

}