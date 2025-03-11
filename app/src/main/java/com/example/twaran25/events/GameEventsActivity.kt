package com.example.twaran25.events

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twaran25.R
import com.example.twaran25.contacts.ContactActivity
import com.example.twaran25.databinding.ActivityGameEventsBinding
import com.example.twaran25.games.Sports
import com.example.twaran25.leaderboard.LeaderBoard

class GameEventsActivity : AppCompatActivity() {
    lateinit var binding: ActivityGameEventsBinding
    private val matchList = listOf(
        Event("8:00 AM", "Football", "05.03.2025", "New Ground", "IIIT Gwalior", "IIIT Delhi"),
        Event("8:00 AM", "Football", "05.03.2025", "New Ground", "IIIT Gwalior", "IIIT Delhi"),
        Event("8:00 AM", "Football", "05.03.2025", "New Ground", "IIIT Gwalior", "IIIT Delhi"),
        Event("8:00 AM", "Football", "05.03.2025", "New Ground", "IIIT Gwalior", "IIIT Delhi"),
        Event("8:00 AM", "Football", "05.03.2025", "New Ground", "IIIT Gwalior", "IIIT Delhi"),
        Event("8:00 AM", "Football", "05.03.2025", "New Ground", "IIIT Gwalior", "IIIT Delhi"),
        Event("8:00 AM", "Football", "05.03.2025", "New Ground", "IIIT Gwalior", "IIIT Delhi"),
        Event("8:00 AM", "Football", "05.03.2025", "New Ground", "IIIT Gwalior", "IIIT Delhi"),
        Event("8:00 AM", "Football", "05.03.2025", "New Ground", "IIIT Gwalior", "IIIT Delhi"),
        Event("8:00 AM", "Football", "05.03.2025", "New Ground", "IIIT Gwalior", "IIIT Delhi"),
        Event("10:00 AM", "Basketball", "05.03.2025", "Old Ground", "IIIT Hyderabad", "IIIT Bangalore"),
        Event("2:00 PM", "Cricket", "06.03.2025", "Main Stadium", "IIIT Allahabad", "IIIT Pune")
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameEventsBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val recyclerView: RecyclerView = findViewById(R.id.events_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = GameMatchAdapter(matchList)

        binding.btnContact.setOnClickListener{
            val intent = Intent(this, ContactActivity::class.java)
            startActivity(intent)
        }
        binding.btnEvents.setOnClickListener{
            val intent = Intent(this, GameEventsActivity::class.java)
            startActivity(intent)
        }
        binding.btnLeaderboard.setOnClickListener{
            val intent = Intent(this, LeaderBoard::class.java)
            startActivity(intent)
        }
        binding.btnMatches.setOnClickListener{
            val intent = Intent(this, Sports::class.java)
            startActivity(intent)
        }
    }
}