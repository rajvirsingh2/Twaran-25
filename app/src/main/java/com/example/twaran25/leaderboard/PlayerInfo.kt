package com.example.twaran25.leaderboard

import androidx.annotation.DrawableRes

data class PlayerInfo(
    @DrawableRes val image:Int,
    val name :String,
    val place:Int,
    val college:String,
)
