package com.example.twaran25.games

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twaran25.R
import com.example.twaran25.leaderboard.LeaderBoard

class Sports : AppCompatActivity() {

    private val mensSports = listOf(
        Game("Swimming", "Make a splash, chase the dash!", R.drawable.swimming),
        Game("Football", "Kick hard, play smart, win big!", R.drawable.football),
        Game("Athletics", "Run fast, jump high, break limits!", R.drawable.atheletic),
        Game("Cricket", "Smash boundaries, seize glory!", R.drawable.cricket),
        Game("Tennis", "Serve, smash, and conquer!", R.drawable.tennis),
        Game("Table Tennis", "Fast hands, quick wins!", R.drawable.table_tennis),
    )

    private val womenSports = listOf(
        Game("Women Swimming", "Make a splash, chase the dash!", R.drawable.swimming),
        Game("Women Football", "Kick hard, play smart, win big!", R.drawable.football),
        Game("Women Athletics", "Run fast, jump high, break limits!", R.drawable.atheletic),
        Game("Women Cricket", "Smash boundaries, seize glory!", R.drawable.cricket),
        Game("Women Tennis", "Serve, smash, and conquer!", R.drawable.tennis),
        Game("Women Table Tennis", "Fast hands, quick wins!", R.drawable.table_tennis),
    )

    private lateinit var recyclerView: RecyclerView
    private lateinit var filterText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sports)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerView)
        val filterButton = findViewById<ImageButton>(R.id.filter)
        filterText = findViewById(R.id.filter_text)

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        updateSportsList(mensSports) // Default: Show Men's Sports

        filterButton.setOnClickListener { view ->
            val popupMenu = PopupMenu(this, view).apply {
                menuInflater.inflate(R.menu.filter_menu, menu)
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.filter_men -> updateSportsList(mensSports, "Men")
                        R.id.filter_women -> updateSportsList(womenSports, "Women")
                    }
                    true
                }
            }
            popupMenu.show()
        }
    }

    private fun updateSportsList(sportsList: List<Game>, category: String = "Men") {
        filterText.text = category
        recyclerView.adapter = GameAdapter(sportsList) {
            val intent=Intent(this,LeaderBoard::class.java)
            this.startActivity(intent)
        }
    }
}
