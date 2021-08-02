package com.example.smartgames.gui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartgames.R
import com.example.smartgames.adapter.BuyGameAdapter
import com.example.smartgames.api.GamesCall
import com.example.smartgames.api.RetrofitApi
import com.example.smartgames.models.Game
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityBuyGame : AppCompatActivity() {
    private var gameId: Long = 0

    lateinit var rvBuyGame: RecyclerView
    lateinit var adapterBuyGame: BuyGameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_game)


        rvBuyGame = findViewById(R.id.rv_buy_game)
        rvBuyGame.adapter = adapterBuyGame
        rvBuyGame.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        gameBuy()
    }

    fun updateId(id: Long) {
        gameId = id
    }

    private fun gameBuy() {
        var gameBuy: Game
        val retrofit = RetrofitApi.getRetrofit()
        val gamesCall = retrofit.create(GamesCall::class.java)

        val call = gamesCall.getGameId(gameId)

        call.enqueue(object : Callback<Game>{
            override fun onResponse(call: Call<Game>, response: Response<Game>) {
                gameBuy = response.body()!!

                adapterBuyGame.updateList(gameBuy)
            }

            override fun onFailure(call: Call<Game>, t: Throwable) {
                Toast.makeText(this@ActivityBuyGame, "Ops! Acho que ocorreu um problema.", Toast.LENGTH_SHORT).show()
                Log.e("Erro_CONEXÃO", t.message.toString())
            }

        })
    }
}