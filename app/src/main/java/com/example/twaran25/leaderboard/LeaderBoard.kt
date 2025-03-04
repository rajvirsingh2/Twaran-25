package com.example.twaran25.leaderboard

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twaran25.R

class LeaderBoard : AppCompatActivity() {
    val players= listOf(
        PlayerInfo(R.drawable.avatar_one,"John Doe",1,"IIIT GWALIOR"),
        PlayerInfo(R.drawable.avatar_third,"John Doe",1,"IIIT GWALIOR"),
        PlayerInfo(R.drawable.avatar_third,"John Doe",1,"IIIT GWALIOR"),
        PlayerInfo(R.drawable.avatar_one,"John Doe",1,"IIIT GWALIOR"),
        PlayerInfo(R.drawable.avatar_one,"John Doe",1,"IIIT GWALIOR"),
        PlayerInfo(R.drawable.avatar_one,"John Doe",1,"IIIT GWALIOR")
    )
   private lateinit var recyclerView:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_leader_board)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recyclerView=findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter=PlayerAdapter(players)

    }

}