package com.example.twaran25

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twaran25.DataSource.colleges
import com.example.twaran25.DataSource.events
import com.example.twaran25.databinding.ActivityEventsBinding
import com.example.twaran25.events.Event
import com.example.twaran25.events.GameMatchAdapter

class Events : AppCompatActivity() {
    private lateinit var eventsBinding: ActivityEventsBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        eventsBinding = ActivityEventsBinding.inflate(layoutInflater)
        setContentView(eventsBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val collegeRow: RecyclerView = eventsBinding.collegeList

        collegeRow.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        collegeRow.adapter = CollegeAdapter(colleges) { collegeName ->
            //Refresh the list when a college icon is clicked
            Toast.makeText(this, "Clicked on $collegeName", Toast.LENGTH_SHORT).show()
        }

        val eventRecyclerView: RecyclerView = eventsBinding.eventsRecycler
        eventRecyclerView.adapter = GameMatchAdapter(events)
        eventRecyclerView.layoutManager = LinearLayoutManager(this)


    }
}