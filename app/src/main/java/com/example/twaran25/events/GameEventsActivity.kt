package com.example.twaran25.events

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twaran25.R

class GameEventsActivity : AppCompatActivity() {
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
        enableEdgeToEdge()
        setContentView(R.layout.activity_game_events)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val recyclerView: RecyclerView = findViewById(R.id.events_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = GameMatchAdapter(matchList)
    }
}