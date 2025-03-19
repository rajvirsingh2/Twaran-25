package com.example.twaran25.leaderboard

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.twaran25.AddMatch
import com.example.twaran25.R
import com.example.twaran25.contacts.AdminContact
import com.example.twaran25.databinding.ActivityAdminLeaderboardBinding
import com.example.twaran25.events.AdminEvents
import com.example.twaran25.games.AdminMatches
import com.example.twaran25.data.viewmodel.LeaderboardViewModel

class AdminLeaderboard : AppCompatActivity() {
    private lateinit var binding: ActivityAdminLeaderboardBinding
    private val leaderboardViewModel: LeaderboardViewModel by viewModels()
    private lateinit var adapter: AdminLeaderboardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminLeaderboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AdminLeaderboardAdapter(){
            val intent = Intent(this, AdminUpdateLeaderboardData::class.java)
            intent.putExtra("collegeName", it.collegeName)

            startActivity(intent)
        }
        binding.recyclerView.adapter = adapter

        // Observe LiveData from ViewModel
        leaderboardViewModel.leaderboardLiveData.observe(this) { leaderboardEntries ->
            adapter.updateData(leaderboardEntries)

            // Assign top 3 colleges dynamically
            binding.playerFirstName.text = leaderboardEntries.getOrNull(0)?.collegeName ?: ""
            binding.playerSecondName.text = leaderboardEntries.getOrNull(1)?.collegeName ?: ""
            binding.playerThirdName.text = leaderboardEntries.getOrNull(2)?.collegeName ?: ""

            binding.playerFirstImage.setImageResource(getImageResource(leaderboardEntries.getOrNull(0)?.collegeName))
            binding.playerSecondImage.setImageResource(getImageResource(leaderboardEntries.getOrNull(1)?.collegeName))
            binding.playerThirdImage.setImageResource(getImageResource(leaderboardEntries.getOrNull(2)?.collegeName))

            binding.playerFirstPoints.text = leaderboardEntries.getOrNull(0)?.points.toString()
            binding.playerSecondPoints.text = leaderboardEntries.getOrNull(1)?.points.toString()
            binding.playerThirdPoints.text = leaderboardEntries.getOrNull(2)?.points.toString()

        }

        binding.addmatch.setOnClickListener {
            val intent = Intent(this, AdminAddLeaderboardCollege::class.java)
            startActivity(intent)
        }

        // Fetch leaderboard data
        leaderboardViewModel.fetchLeaderboard()

        navigation()
    }

    private fun navigation(){
        binding.btnContact.setOnClickListener {
            if (javaClass.simpleName != AdminContact::class.java.simpleName) {
                val intent = Intent(this, AdminContact::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        binding.btnEvents.setOnClickListener {
            if (javaClass.simpleName != AdminEvents::class.java.simpleName) {
                val intent = Intent(this, AdminEvents::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        binding.btnLeaderboard.setOnClickListener {
            if (javaClass.simpleName != AdminLeaderboard::class.java.simpleName) {
                val intent = Intent(this, AdminLeaderboard::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        binding.btnMatches.setOnClickListener {
            if (javaClass.simpleName != AdminMatches::class.java.simpleName) {
                val intent = Intent(this, AdminMatches::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }

    // Function to fetch college image from a predefined map
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

}


