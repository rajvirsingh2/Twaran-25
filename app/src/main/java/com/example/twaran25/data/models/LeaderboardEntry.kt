package com.example.twaran25.data.models

data class LeaderboardEntry(
    var collegeName: String = "",
    var goldCount: Int = 0,
    var silverCount: Int = 0,
    var bronzeCount: Int = 0,
    var points: Int = 0
) {
    // Required empty constructor for Firebase
    constructor() : this("", 0, 0, 0, 0)
}
