package com.example.twaran25.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.twaran25.data.models.Matches
import com.example.twaran25.data.repository.FirebaseRepository

class MatchViewModel(private val repository: FirebaseRepository = FirebaseRepository()) : ViewModel() {
    private val _matches = MutableLiveData<List<Matches>>()
    val matches: LiveData<List<Matches>> get() = _matches

    init {
        fetchMatches()
    }

    private fun fetchMatches() {
        repository.fetchMatches { fetchedMatches ->
            _matches.postValue(fetchedMatches)
        }
    }

    fun addMatch(match: Matches) {
        repository.addMatch(match)
    }
}
