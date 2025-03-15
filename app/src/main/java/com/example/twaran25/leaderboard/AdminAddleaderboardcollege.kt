package com.example.twaran25.leaderboard

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.twaran25.R
import com.example.twaran25.data.models.LeaderboardEntry
import com.example.twaran25.data.viewmodel.LeaderboardViewModel
import com.example.twaran25.databinding.ActivityAdminAddleaderboardcollegeBinding

class AdminAddLeaderboardCollege : AppCompatActivity() {
    private lateinit var binding: ActivityAdminAddleaderboardcollegeBinding
    private val leaderboardViewModel: LeaderboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminAddleaderboardcollegeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.teamA.setOnClickListener { setupCollegePopupMenu(it, binding.teamA) }
        binding.btnSubmit.setOnClickListener { addLeaderboardEntry()
        finish()
        }
    }

    private fun setupCollegePopupMenu(view: View, editText: android.widget.EditText) {
        val colleges = listOf(
            "IIIT Gwalior", "IIIT Hyderabad", "IIIT Allahabad", "IIIT Jabalpur", "IIIT Kancheepuram",
            "IIIT Guwahati", "IIIT Vadodara", "IIIT Kota", "IIIT Kalyani", "IIIT Una",
            "IIIT Sonepat", "IIIT Lucknow", "IIIT Dharwad", "IIIT Kottayam", "IIIT Manipur",
            "IIIT Tiruchirappalli", "IIIT Nagpur", "IIIT Pune", "IIIT Ranchi", "IIIT Bhagalpur",
            "IIIT Bhopal", "IIIT Agartala", "IIIT Raichur", "IIIT Surat"
        )

        val popupMenu = PopupMenu(view.context, view).apply {
            colleges.forEachIndexed { index, college ->
                menu.add(0, index, 0, college)
            }
            setOnMenuItemClickListener { item ->
                editText.setText(colleges[item.itemId])
                true
            }
        }
        popupMenu.show()
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