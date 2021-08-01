package com.example.smartgames.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.smartgames.api.UrlApi.Companion.BASE_URL

class RetrofitApi {
    companion object {
        fun getRetrofit(): Retrofit {
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}