package com.example.twaran25.events

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twaran25.R
import com.example.twaran25.data.models.Matches
import com.example.twaran25.data.viewmodel.MatchViewModel
import com.example.twaran25.databinding.ActivityGameEventsBinding

class GameEventsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameEventsBinding
    private val viewModel: MatchViewModel by viewModels()
    private lateinit var adapter: GameMatchAdapter

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
        val progressBar: ProgressBar = findViewById(R.id.progress_bar)
        progressBar.visibility = View.VISIBLE
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize adapter with an empty list


        adapter = GameMatchAdapter(mutableListOf())
        recyclerView.adapter = adapter
        val sportName = intent.getStringExtra("SPORT_NAME") ?: ""
        Log.d("matcheslist", "Received Sport Name: $sportName")

        // Observe the matches LiveData
        viewModel.matches.observe(this) { matches ->
            Log.d("matcheslist", "Final matches list: $matches")
            progressBar.visibility = View.GONE
            adapter.updateData(matches) // Update adapter with new data
        }

        // Fetch matches from ViewModel
        progressBar.visibility = View.VISIBLE
        viewModel.fetchMatchesBySport(sportName)

        binding.day1.setOnClickListener {
            viewModel.fetchMatchesBySportAndDay(sportName , 1)

        }
        binding.day2.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            viewModel.fetchMatchesBySportAndDay(sportName , 2)
            progressBar.visibility = View.GONE
        }
        binding.day3.setOnClickListener {
            viewModel.fetchMatchesBySportAndDay(sportName , 3)}
        binding.day4.setOnClickListener {
            viewModel.fetchMatchesBySportAndDay(sportName , 3)}
        binding.bakcbtn.setOnClickListener {
            finish()
        }
        viewModel.matches.observe(this) { matches ->

        }
        }

    }

