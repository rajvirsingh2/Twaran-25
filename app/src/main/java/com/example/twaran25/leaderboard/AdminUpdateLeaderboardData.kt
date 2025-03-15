package com.example.twaran25.leaderboard

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.twaran25.data.models.LeaderboardEntry
import com.example.twaran25.data.viewmodel.LeaderboardViewModel
import com.example.twaran25.databinding.ActivityAdminUpdateLeaderboardDataBinding

class AdminUpdateLeaderboardData : AppCompatActivity() {
    private lateinit var binding: ActivityAdminUpdateLeaderboardDataBinding
    private val leaderboardViewModel: LeaderboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAdminUpdateLeaderboardDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handling system bars
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get the leaderboard entry ID from intent
        val entryId = intent.getStringExtra("collegeName")
        if (entryId != null) {
            Log.d("AdminUpdateLeaderboard", "Received collegeName: $entryId")
            fetchLeaderboardData(entryId)
        } else {
            Log.e("AdminUpdateLeaderboard", "Leaderboard entry ID is missing")
        }

        binding.btnSubmit.setOnClickListener { addLeaderboardEntry()
            finish()
        }

    }

    private fun fetchLeaderboardData(entryId: String) {
        Log.d("AdminUpdateLeaderboard", "Fetching leaderboard data for: $entryId")

        leaderboardViewModel.fetchSingleLeaderboardEntry(entryId)

        leaderboardViewModel.leaderboardEntry.observe(this) { entry ->
            if (entry != null) {
                Log.d("AdminUpdateLeaderboard", "Fetched entry: $entry")
                binding.teamA.setText(entry.collegeName)  // Read-only field
                binding.gold.setText(entry.goldCount.toString())
                binding.silver.setText(entry.silverCount.toString())
                binding.bronze.setText(entry.bronzeCount.toString())
                binding.points.setText(entry.points.toString())
            } else {
                Log.e("AdminUpdateLeaderboard", "Leaderboard entry not found for ID: $entryId")
            }
        }
    }

    private fun addLeaderboardEntry() {
        val collegeName = binding.teamA.text.toString()
        val gold = binding.gold.text.toString().toIntOrNull() ?: 0
        val silver = binding.silver.text.toString().toIntOrNull() ?: 0
        val bronze = binding.bronze.text.toString().toIntOrNull() ?: 0
        val points = binding.points.text.toString().toIntOrNull() ?: 0

        if (collegeName.isEmpty()) {
            Toast.makeText(this, "Please select a college", Toast.LENGTH_SHORT).show()
            return
        }

        val entry = LeaderboardEntry(collegeName, gold, silver, bronze, points)
        leaderboardViewModel.addLeaderboardEntry(entry)
        Toast.makeText(this, "Leaderboard entry added successfully", Toast.LENGTH_SHORT).show()
    }

}
