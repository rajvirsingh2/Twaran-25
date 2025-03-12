package com.example.twaran25.data.models.user

data class LeaderboardEntry(
    val collegeName: String,
    val goldCount: Int = 0,
    val silverCount: Int = 0,
    val bronzeCount: Int = 0,
    val points: Int = 0
)
