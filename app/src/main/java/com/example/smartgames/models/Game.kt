package com.example.smartgames.models

import com.google.gson.annotations.SerializedName

data class Game(
    var id: Long,
    var name: String,
    var description: String,
    var price: Double,
    var image: String,
    @SerializedName("Store")
    var store: Store,
    var plataform: Plataform
)
class Store(
    var id: Long,
    var name: String
)
class Plataform(
    var id: Long,
    var name: String
)