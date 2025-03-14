package com.example.twaran25.data.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.twaran25.data.models.LeaderboardEntry
import com.example.twaran25.data.repository.FirebaseRepository

class LeaderboardViewModel : ViewModel() {
    private val repository = FirebaseRepository()

    //update medal count
    fun updateMedalCount(entry: LeaderboardEntry) {
        repository.updateMedalCount(entry)
        Log.d("LeaderboardViewModel", "Updated medals for ${entry.collegeName}: Gold=${entry.goldCount}, Silver=${entry.silverCount}, Bronze=${entry.bronzeCount}, Points=${entry.points}")
    }

    //fetch leaderboard data
    fun fetchLeaderboard() {
        repository.fetchLeaderboard { leaderboardEntries ->
            for (entry in leaderboardEntries) {
                Log.d("LeaderboardViewModel", "College: ${entry.collegeName}, Gold: ${entry.goldCount}, Silver: ${entry.silverCount}, Bronze: ${entry.bronzeCount}, Points: ${entry.points}")
            }
        }
    }
}