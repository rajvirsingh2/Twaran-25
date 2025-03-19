package com.example.twaran25.leaderboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.twaran25.Events
import com.example.twaran25.R
import com.example.twaran25.contacts.ContactActivity
import com.example.twaran25.data.models.LeaderboardEntry
import com.example.twaran25.data.viewmodel.LeaderboardViewModel
import com.example.twaran25.databinding.ActivityLeaderBoardBinding
import com.example.twaran25.games.Sports

class LeaderBoard : AppCompatActivity() {
    private lateinit var binding: ActivityLeaderBoardBinding

    private var firstPoints: Int = 0
    private var secondPoints: Int = 0
    private var thirdPoints: Int = 0
    private val leaderboardViewModel: LeaderboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize ViewBinding
        binding = ActivityLeaderBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Fetch leaderboard from Firebase
        leaderboardViewModel.fetchLeaderboard()

        // Observe LiveData and update UI
        leaderboardViewModel.leaderboardLiveData.observe(this) { leaderboard ->
            if (leaderboard.isNotEmpty()) {
                binding.leaderboard.visibility = View.VISIBLE
                updateUI(leaderboard)
            } else {
                Log.e("LeaderBoard", "Leaderboard is empty!")
            }
        }

        // Navigation Button Listeners
        navigation()
    }

    private fun updateUI(leaderboard: List<LeaderboardEntry>) {
        // Sort by points in descending order
        val sortedLeaderboard = leaderboard.sortedByDescending { it.points }

        // Set Top 3 Colleges
        binding.collegeFirstName.text = sortedLeaderboard.getOrNull(0)?.collegeName ?: ""
        binding.collegeFirstImage.setImageResource(
            getImageResource(sortedLeaderboard.getOrNull(0)?.collegeName)
        )
        binding.collegeFirstPoints.text = sortedLeaderboard.getOrNull(0)?.points?.toString() ?: ""

        binding.collegeSecondName.text = sortedLeaderboard.getOrNull(1)?.collegeName ?: ""
        binding.collegeSecondImage.setImageResource(
            getImageResource(sortedLeaderboard.getOrNull(1)?.collegeName)
        )
        binding.collegeSecondPoints.text = sortedLeaderboard.getOrNull(1)?.points?.toString() ?: ""

        binding.collegeThirdName.text = sortedLeaderboard.getOrNull(2)?.collegeName ?: ""
        binding.collegeThirdImage.setImageResource(
            getImageResource(sortedLeaderboard.getOrNull(2)?.collegeName)
        )
        binding.collegeThirdPoints.text = sortedLeaderboard.getOrNull(2)?.points?.toString() ?: ""

        firstPoints = sortedLeaderboard.getOrNull(0)?.points ?: 0
        secondPoints = sortedLeaderboard.getOrNull(1)?.points ?: 0
        thirdPoints = sortedLeaderboard.getOrNull(2)?.points ?: 0

        binding.collegeFirstPoints.text = "$firstPoints"
        binding.collegeSecondPoints.text = "$secondPoints"
        binding.collegeThirdPoints.text = "$thirdPoints"

        binding.firstCollege.visibility = if (firstPoints == 0) View.GONE else View.VISIBLE
        binding.secondCollege.visibility = if (secondPoints == 0) View.GONE else View.VISIBLE
        binding.thirdCollege.visibility = if (thirdPoints == 0) View.GONE else View.VISIBLE

        binding.collegeFirstName.visibility = if (firstPoints == 0) View.GONE else View.VISIBLE
        binding.collegeSecondName.visibility = if (secondPoints == 0) View.GONE else View.VISIBLE
        binding.collegeThirdName.visibility = if (thirdPoints == 0) View.GONE else View.VISIBLE

        binding.collegeFirstImage.visibility = if (firstPoints == 0) View.GONE else View.VISIBLE
        binding.collegeSecondImage.visibility = if (secondPoints == 0) View.GONE else View.VISIBLE
        binding.collegeThirdImage.visibility = if (thirdPoints == 0) View.GONE else View.VISIBLE

        // Update RecyclerView Adapter
        val adapter = binding.recyclerView.adapter as? LeaderboardAdapter
        if (adapter == null) {
            binding.recyclerView.adapter = LeaderboardAdapter().apply { updateData(sortedLeaderboard) }
        } else {
            adapter.updateData(sortedLeaderboard)
        }
    }

    private fun getImageResource(collegeName: String?): Int {
        val mappedEntries = mapOf(
            "IIIT Gwalior" to R.drawable.iiitgwalior,
            "IIIT Una" to R.drawable.iiituna,
            "IIIT Kota" to R.drawable.iiitkota,
            "IIIT Pune" to R.drawable.iiitpune,
            "IIIT Surat" to R.drawable.iiitsurat,
            "IIIT Kalyani" to R.drawable.iiit_kalyani,
            "IIIT Agartala" to R.drawable.iiitagartala,
            "IIIT Allahabad" to R.drawable.iiitallahabad,
            "IIIT Bhagalpur" to R.drawable.iiitbhagalpur,
            "IIIT Bhopal" to R.drawable.iiitbhopal,
            "IIIT Dharwad" to R.drawable.iiitdharwad,
            "IIIT Guwahati" to R.drawable.iiitguwahati,
            "IIIT kurnool" to R.drawable.iiitkurnool,
            "IIIT Jabalpur" to R.drawable.iiitjabalpur,
            "IIIT Kancheepuram" to R.drawable.iiitkancheepuram,
            "IIIT Kottayam" to R.drawable.iiitkottatam,
            "IIIT Lucknow" to R.drawable.iiitlucknow,
            "IIIT Manipur" to R.drawable.iiitmanipur,
            "IIIT Nagpur" to R.drawable.iiitnagpur,
            "IIIT Raichur" to R.drawable.iiitraichur,
            "IIIT Ranchi" to R.drawable.iiitranchi,
            "IIIT Sonepat" to R.drawable.iiitsonepat,
            "IIIT Tiruchirappalli" to R.drawable.iiittiruchirappalli,
            "IIIT Vadodara" to R.drawable.iiitvadodra
        )
        return mappedEntries[collegeName] ?: R.drawable.iiitgwalior
    }

    private fun navigation() {
        binding.btnContact.setOnClickListener {
            startActivity(Intent(this, ContactActivity::class.java))
        }

        binding.btnEvents.setOnClickListener {
            startActivity(Intent(this, Events::class.java))
        }

        binding.btnLeaderboard.setOnClickListener {
            startActivity(Intent(this, LeaderBoard::class.java))
        }

        binding.btnMatches.setOnClickListener {
            startActivity(Intent(this, Sports::class.java))
        }
    }
}
