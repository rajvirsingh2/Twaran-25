package com.example.twaran25.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.twaran25.data.models.LeaderboardEntry
import com.example.twaran25.data.models.Matches
import com.example.twaran25.data.repository.FirebaseRepository

class LeaderboardViewModel : ViewModel() {
    private val repository = FirebaseRepository()

    // Mutable LiveData to observe the leaderboard list
    private val _leaderboardLiveData = MutableLiveData<List<LeaderboardEntry>>()
    val leaderboardLiveData: LiveData<List<LeaderboardEntry>> get() = _leaderboardLiveData

    // Mutable LiveData to observe a single leaderboard entry
    private val _leaderboardEntry = MutableLiveData<LeaderboardEntry?>()
    val leaderboardEntry: LiveData<LeaderboardEntry?> get() = _leaderboardEntry

    // Fetch all leaderboard data and update LiveData
    fun fetchLeaderboard() {
        repository.fetchLeaderboard { leaderboardEntries ->
            val sortedLeaderboard = leaderboardEntries.sortedByDescending { it.points }
            _leaderboardLiveData.postValue(sortedLeaderboard) // Updating UI with sorted data

            for (entry in sortedLeaderboard) {
                Log.d(
                    "LeaderboardViewModel",
                    "College: ${entry.collegeName}, Gold: ${entry.goldCount}, Silver: ${entry.silverCount}, Bronze: ${entry.bronzeCount}, Points: ${entry.points}"
                )
            }
        }
    }

    // Add a new leaderboard entry to Firebase
    fun addLeaderboardEntry(entry: LeaderboardEntry) {
        repository.addLeaderboard(entry)
        Log.d("LeaderboardViewModel", "Added new leaderboard entry for ${entry.collegeName}")
    }

    // Update medal count in Firebase
    fun updateMedalCount(entry: LeaderboardEntry) {
        repository.updateMedalCount(entry)
        Log.d(
            "LeaderboardViewModel",
            "Updated medals for ${entry.collegeName}: Gold=${entry.goldCount}, Silver=${entry.silverCount}, Bronze=${entry.bronzeCount}, Points=${entry.points}"
        )
    }

    // Fetch a single leaderboard entry by ID
    fun fetchSingleLeaderboardEntry(collegeName: String) {
        repository.fetchLeaderboardEntry(collegeName) { entry ->
            if (entry != null) {
                _leaderboardEntry.postValue(entry)
                Log.d("LeaderboardViewModel", "Fetched entry: $entry")
            } else {
                Log.e("LeaderboardViewModel", "Leaderboard entry not found for $collegeName")
            }
        }
    }
}
