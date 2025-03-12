package com.example.twaran25.events

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twaran25.DataSource
import com.example.twaran25.R
import com.example.twaran25.contacts.ContactActivity
import com.example.twaran25.databinding.ActivityGameEventsBinding
import com.example.twaran25.games.Sports
import com.example.twaran25.leaderboard.LeaderBoard

class GameEventsActivity : AppCompatActivity() {
    lateinit var binding: ActivityGameEventsBinding
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
        recyclerView.adapter = GameMatchAdapter(DataSource.events)

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