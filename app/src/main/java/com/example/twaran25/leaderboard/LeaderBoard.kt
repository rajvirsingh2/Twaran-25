package com.example.twaran25.leaderboard

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.twaran25.Events
import com.example.twaran25.R
import com.example.twaran25.contacts.ContactActivity
import com.example.twaran25.databinding.ActivityLeaderBoardBinding
import com.example.twaran25.games.Sports

class LeaderBoard : AppCompatActivity() {
    private lateinit var binding: ActivityLeaderBoardBinding

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

        // Initialize ViewBinding
        binding = ActivityLeaderBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup RecyclerView
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
