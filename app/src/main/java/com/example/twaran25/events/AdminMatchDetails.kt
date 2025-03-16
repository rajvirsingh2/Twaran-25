package com.example.twaran25.events

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
import com.example.twaran25.AddMatch
import com.example.twaran25.DataSource
import com.example.twaran25.R
import com.example.twaran25.data.viewmodel.MatchViewModel
import com.example.twaran25.databinding.ActivityAdminMatchDetailsBinding

class AdminMatchDetails : AppCompatActivity() {
    lateinit var binding: ActivityAdminMatchDetailsBinding
    private val viewModel: MatchViewModel by viewModels()
    private lateinit var adapter: AdminMatchesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAdminMatchDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




        binding.eventsRecycler.layoutManager = LinearLayoutManager(this)

        adapter = AdminMatchesAdapter(this,mutableListOf())
        binding.eventsRecycler.adapter = adapter
        val sportName = intent.getStringExtra("SPORT_NAME") ?: ""
        Log.d("matcheslist", "Received Sport Name: $sportName")

        // Observe the matches LiveData
        viewModel.matches.observe(this) { matches ->
            Log.d("matcheslist", "Final matches list: $matches")
            adapter.updateData(matches) // Update adapter with new data
        }

        // Fetch matches from ViewModel
        viewModel.fetchMatchesBySport(sportName)


        binding.backarrow.setOnClickListener {
            finish()
        }
        binding.addmatch.setOnClickListener {
            val intent: Intent = Intent(this, AddMatch::class.java)
            startActivity(intent)
        }
        binding.Day1.setOnClickListener {
            viewModel.fetchMatchesBySportAndDay(sportName , 1)

        }
        binding.Day2.setOnClickListener {
            viewModel.fetchMatchesBySportAndDay(sportName , 2)}
        binding.day3.setOnClickListener {
            viewModel.fetchMatchesBySportAndDay(sportName , 3)}
    }
}