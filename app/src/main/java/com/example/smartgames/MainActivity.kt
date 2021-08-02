package com.example.smartgames

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartgames.adapter.GamesAdapter
import com.example.smartgames.api.GamesCall
import com.example.smartgames.api.RetrofitApi
import com.example.smartgames.gui.ActivityBuyGame
import com.example.smartgames.models.Game
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var toolbar: Toolbar
    lateinit var rvListGames: RecyclerView
    lateinit var adapterGames: GamesAdapter

    lateinit var buttonMoreDetails: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        toolbar.title = "Smart Games"
        setSupportActionBar(toolbar)

        buttonMoreDetails = findViewById(R.id.button_more_detail)
        buttonMoreDetails.setOnClickListener(this)
        adapterGames = GamesAdapter(this)

        rvListGames = findViewById(R.id.rv_games_list)
        rvListGames.adapter = adapterGames
        rvListGames.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        listGames()
    }

    private fun listGames() {
        var gamesList: List<Game>
        val retrofit = RetrofitApi.getRetrofit()
        val gamesCall = retrofit.create(GamesCall::class.java)

        val call = gamesCall.getGames()

        call.enqueue(object : Callback<List<Game>> {
            override fun onResponse(call: Call<List<Game>>, response: Response<List<Game>>) {
                gamesList = response.body()!!

                adapterGames.updateList(gamesList)
            }

            override fun onFailure(call: Call<List<Game>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Ops! Acho que ocorreu um problema.", Toast.LENGTH_SHORT).show()
                Log.e("Erro_CONEXÃO", t.message.toString())
            }

        })
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.button_more_detail -> {
                val intent = Intent(this, ActivityBuyGame::class.java)
                startActivity(intent)
            }
        }
    }
}