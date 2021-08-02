package com.example.smartgames.adapter

import android.content.Context
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.smartgames.gui.ActivityBuyGame
import com.example.smartgames.R
import com.example.smartgames.models.Game
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup


class BuyGameAdapter(val context: Context) : RecyclerView.Adapter<BuyGameAdapter.Holder>() {
    var gamesBuy = emptyList<Game>()

    fun updateList(list: Game) {
        gamesBuy = listOf(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.holder_buy_game, parent, false)

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val gamePosition = gamesBuy[position]

        holder.nameGameBuy.text = gamePosition.name

        holder.priceGameBuy.text = "R$ ${String.format("%.2f", gamePosition.price)}"
        Glide.with(holder.itemView.context).load(gamePosition.image).into(holder.imageGameBuy)

        holder.descriptionGameBuy.text = gamePosition.description

        for(plataform in gamePosition.plataform){
            val chip = Chip(ContextThemeWrapper(context, R.style.ChipTextAppearence), null, 0)

            chip.text = plataform.name
            holder.chipGroupPlataforms.addView(chip)
        }
    }

    override fun getItemCount(): Int {
        return gamesBuy.size
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val imageGameBuy: ImageView  = view.findViewById(R.id.buy_image_game)
        val nameGameBuy: TextView = view.findViewById(R.id.buy_name_game)
        val priceGameBuy: TextView = view.findViewById(R.id.buy_price_game)
        val descriptionGameBuy: TextView = view.findViewById(R.id.buy_description_game)
        val chipGroupPlataforms: ChipGroup = view.findViewById(R.id.chip_group_plataform)
    }
}