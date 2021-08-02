package com.example.smartgames.api

import com.example.smartgames.models.Game
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GamesCall {

    @GET("games")
    fun getGames() : Call<List<Game>>

    @GET("games/:id")
    fun getGameId(@Path("id") id: Long) : Call<Game>
}