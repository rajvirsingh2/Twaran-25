package com.example.twaran25.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.twaran25.data.models.Matches
import com.example.twaran25.data.repository.FirebaseRepository

class MatchViewModel(private val repository: FirebaseRepository = FirebaseRepository()) : ViewModel() {
    private val _matches = MutableLiveData<List<Matches>>()
    val matches: LiveData<List<Matches>> get() = _matches

    private val _match = MutableLiveData<Matches?>()  // LiveData for a single match
    val match: LiveData<Matches?> get() = _match

    init {
        fetchMatches()
    }

    fun fetchMatches() {
        repository.fetchMatches { fetchedMatches ->
            _matches.postValue(fetchedMatches)
        }
    }

    fun fetchMatch(matchId: String) {
        repository.fetchMatch(matchId) { fetchedMatch ->
            _match.postValue(fetchedMatch)  // Store a single match separately
        }
    }

    fun addMatch(match: Matches) {
        repository.addMatch(match)
    }
    fun fetchMatchesBySport(sportName: String) {
        repository.fetchMatchesBySport(sportName) { fetchedMatches ->
            Log.d("MatchViewModel", "Updating LiveData with ${fetchedMatches.size} matches")
            _matches.postValue(fetchedMatches)
        }
    }

    fun fetchMatchesByDay(day: Int) {
        repository.fetchMatchesByDay(day) { fetchedMatches ->
            _matches.postValue(fetchedMatches)
        }
    }
    fun fetchMatchesByTeam(teamName: String) {
        repository.fetchMatchesByTeam(teamName) { fetchedMatches ->
            _matches.postValue(fetchedMatches)
        }
    }
    fun fetchMatchesByTeamAndDay(teamName: String, day: Int) {
        repository.fetchMatchesByTeamAndDay(teamName, day) { fetchedMatches ->
            _matches.postValue(fetchedMatches)
        }
    }
    fun fetchMatchesBySportAndDay(sportName: String, day: Int) {
        repository.fetchMatchesBySportAndDay(sportName, day) { fetchedMatches ->
            _matches.postValue(fetchedMatches)
        }
    }
    fun deleteMatch(matchId: String) {
        repository.deleteMatch(matchId) { success ->
            if (success) {
                fetchMatches() // Refresh the list after deletion
            } else {
                Log.e("MatchViewModel", "Failed to delete match with ID: $matchId")
            }
        }
    }


}
