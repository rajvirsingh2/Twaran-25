package com.example.twaran25.games

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twaran25.Events
import com.example.twaran25.R
import com.example.twaran25.contacts.AdminContact
import com.example.twaran25.contacts.ContactActivity
import com.example.twaran25.databinding.ActivityAdminMatchesBinding
import com.example.twaran25.events.AdminEvents
import com.example.twaran25.events.AdminMatchDetails
import com.example.twaran25.leaderboard.AdminLeaderboard
import com.example.twaran25.leaderboard.LeaderBoard
import com.google.android.material.textfield.TextInputEditText

class AdminMatches : AppCompatActivity() {

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
    private lateinit var searchBar: TextInputEditText
    private lateinit var adapter: AdminGameAdapter
    private var currentSportsList: List<Game> = mensSports // Default to men's sports

    lateinit var binding: ActivityAdminMatchesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAdminMatchesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerView)
        val filterButton = findViewById<ImageButton>(R.id.filter)
        filterText = findViewById(R.id.filter_text)
        searchBar = findViewById(R.id.sports_name)

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        updateSportsList(mensSports, "Men") // Default: Show Men's Sports

        // Filter selection menu
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
        navigation()
        // Real-time search filtering
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(s.toString()) // Filter sports dynamically
            }

            override fun afterTextChanged(s: Editable?) {}
        })



    }

    private fun navigation(){
        binding.btnContact.setOnClickListener {
            if (javaClass.simpleName != AdminContact::class.java.simpleName) {
                val intent = Intent(this, AdminContact::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        binding.btnEvents.setOnClickListener {
            if (javaClass.simpleName != AdminEvents::class.java.simpleName) {
                val intent = Intent(this, AdminEvents::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        binding.btnLeaderboard.setOnClickListener {
            if (javaClass.simpleName != AdminLeaderboard::class.java.simpleName) {
                val intent = Intent(this, AdminLeaderboard::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        binding.btnMatches.setOnClickListener {
            if (javaClass.simpleName != AdminMatches::class.java.simpleName) {
                val intent = Intent(this, AdminMatches::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }

    private fun updateSportsList(sportsList: List<Game>, category: String) {
        filterText.text = category
        currentSportsList = sportsList // Update the active sports list
        adapter = AdminGameAdapter(currentSportsList) {
            val intent = Intent(this, AdminMatchDetails::class.java)
            startActivity(intent)
        }
        recyclerView.adapter = adapter
    }
    }
