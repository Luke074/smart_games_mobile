package com.example.smartgames.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.smartgames.R
import com.example.smartgames.models.Game


class GamesAdapter(val context: Context) : RecyclerView.Adapter<GamesAdapter.Holder>() {
    var gamesList = emptyList<Game>()

    fun updateList(list: List<Game>) {
        gamesList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.holder_games, parent, false)

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val gamePosition = gamesList[position]

        holder.nameGame.text = gamePosition.name

        holder.priceGame.text = "R$ ${gamePosition.price}"
        Glide.with(holder.itemView.context).load(gamePosition.image).into(holder.imageGame)
    }

    override fun getItemCount(): Int {
        return gamesList.size
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val imageGame: ImageView  = view.findViewById(R.id.image_game)
        val nameGame: TextView = view.findViewById(R.id.name_game)
        val priceGame: TextView = view.findViewById(R.id.price_game)
        val buttonDetails: Button = view.findViewById(R.id.button_more_detail)
    }
}