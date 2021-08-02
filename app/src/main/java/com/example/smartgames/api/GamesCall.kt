package com.example.smartgames.api

import com.example.smartgames.models.Game
import retrofit2.Call

interface GamesCall {

    fun getGames() : Call<List<Game>>
}