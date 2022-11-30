package com.example.api.Retrofit

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object RetrofitInst {

    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val api: ApiRaM by lazy {
        retrofit.create(ApiRaM::class.java)
    }

}