package com.example.twaran25

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twaran25.DataSource.colleges
import com.example.twaran25.contacts.ContactActivity
import com.example.twaran25.data.viewmodel.MatchViewModel
import com.example.twaran25.databinding.ActivityEventsBinding
import com.example.twaran25.events.GameMatchAdapter
import com.example.twaran25.games.Sports
import com.example.twaran25.leaderboard.LeaderBoard


class Events : AppCompatActivity() {
    private lateinit var binding: ActivityEventsBinding
    private val viewModel: MatchViewModel by viewModels()
    private lateinit var adapter: GameMatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEventsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val collegeRow: RecyclerView = binding.collegeList

        collegeRow.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        collegeRow.adapter = CollegeAdapter(colleges) { collegeName ->
            //Refresh the list when a college icon is clicked
            viewModel.fetchMatchesByTeam(collegeName)
        }

        // Initialize RecyclerView
        val eventRecyclerView: RecyclerView = binding.eventsRecycler
        eventRecyclerView.layoutManager = LinearLayoutManager(this)

        adapter = GameMatchAdapter(mutableListOf())
        eventRecyclerView.adapter = adapter

        // Observe LiveData and update RecyclerView
        viewModel.matches.observe(this) { matches ->
            Log.d("EventsActivity", "Fetched ${matches.size} matches")
            adapter.updateData(matches)
        }

        // Fetch all matches from ViewModel
        viewModel.fetchMatches()
        navigation()
    }

    private fun navigation(){
        binding.btnContact.setOnClickListener {
            if (javaClass.simpleName != ContactActivity::class.java.simpleName) {
                val intent = Intent(this, ContactActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        binding.btnEvents.setOnClickListener {
            if (javaClass.simpleName != Events::class.java.simpleName) {
                val intent = Intent(this, Events::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        binding.btnLeaderboard.setOnClickListener {
            if (javaClass.simpleName != LeaderBoard::class.java.simpleName) {
                val intent = Intent(this, LeaderBoard::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        binding.btnMatches.setOnClickListener {
            if (javaClass.simpleName != Sports::class.java.simpleName) {
                val intent = Intent(this, Sports::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }

}
