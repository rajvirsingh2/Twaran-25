package com.example.twaran25.data.models.user

data class Matches(
    val matchId: String,
    val teamA: String,
    val teamB: String,
    val day: Int = 0,
    val time: String,
    val date: String,
    val venue: String,
    val teamAScore: Int,
    val teamBScore: Int,
    val winner: Int = -1
)
