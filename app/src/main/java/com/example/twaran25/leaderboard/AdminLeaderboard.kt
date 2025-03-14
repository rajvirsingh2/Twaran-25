package com.example.twaran25.leaderboard

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.twaran25.Events
import com.example.twaran25.R
import com.example.twaran25.contacts.AdminContact
import com.example.twaran25.contacts.ContactActivity
import com.example.twaran25.databinding.ActivityAdminLeaderboardBinding
import com.example.twaran25.events.AdminEvents
import com.example.twaran25.games.AdminMatches
import com.example.twaran25.games.Sports

class AdminLeaderboard : AppCompatActivity() {
    private lateinit var binding: ActivityAdminLeaderboardBinding

    val players = listOf(
        PlayerInfo(R.drawable.avatar_one, "Rezaul", 1, "IIIT GWALIOR"),
        PlayerInfo(R.drawable.avatar_third, "Owais", 2, "IIIT GWALIOR"),
        PlayerInfo(R.drawable.avatar_third, "Rajat ", 3, "IIIT GWALIOR"),
        PlayerInfo(R.drawable.avatar_one, "John Doe", 4, "IIIT GWALIOR"),
        PlayerInfo(R.drawable.avatar_one, "John Doe", 5, "IIIT GWALIOR"),
        PlayerInfo(R.drawable.avatar_one, "John Doe", 6, "IIIT GWALIOR")
    )
    val first = players.find {
        it.place == 1
    }
    val second = players.find {
        it.place == 2
    }
    val third = players.find {
        it.place == 3
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAdminLeaderboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = PlayerAdapter(players)

        binding.playerFirstName.text= first?.name ?: ""
        first?.let { binding.playerFirstImage.setImageResource(it.image) }

        binding.playerSecondName.text= second?.name ?: ""
        second?.let { binding.playerSecondImage.setImageResource(it.image) }

        binding.playerThirdName.text= third?.name ?: ""
        third?.let { binding.playerThirdImage.setImageResource(it.image) }

        binding.btnContact.setOnClickListener {
            if (javaClass.simpleName != ContactActivity::class.java.simpleName) {
                val intent = Intent(this, ContactActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
        navigation()
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
}