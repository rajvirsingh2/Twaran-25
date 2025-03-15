package com.example.twaran25.data.models

import com.example.twaran25.games.Sports

data class Matches(
    val matchId: String = "",
    val sportsName: String = "",
    val teamA: String = "",
    val teamB: String = "",
    val day: Int = 0,
    val time: String = "",
    val date: String = "",
    val venue: String = "",
    val teamAScore: Int = 0,
    val teamBScore: Int = 0,
    val teamAResult: Int = 0,
    val teamBResult: Int = 0,
)
