package com.example.twaran25

import android.content.Intent
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
import com.example.twaran25.contacts.ContactActivity
import com.example.twaran25.databinding.ActivityEventsBinding
import com.example.twaran25.events.Event
import com.example.twaran25.events.GameEventsActivity
import com.example.twaran25.events.GameMatchAdapter
import com.example.twaran25.games.Sports
import com.example.twaran25.leaderboard.LeaderBoard

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

        eventsBinding.btnContact.setOnClickListener {
            if (javaClass.simpleName != ContactActivity::class.java.simpleName) {
                val intent = Intent(this, ContactActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        eventsBinding.btnEvents.setOnClickListener {
            if (javaClass.simpleName != Events::class.java.simpleName) {
                val intent = Intent(this, Events::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        eventsBinding.btnLeaderboard.setOnClickListener {
            if (javaClass.simpleName != LeaderBoard::class.java.simpleName) {
                val intent = Intent(this, LeaderBoard::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        eventsBinding.btnMatches.setOnClickListener {
            if (javaClass.simpleName != Sports::class.java.simpleName) {
                val intent = Intent(this, Sports::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }


    }
}