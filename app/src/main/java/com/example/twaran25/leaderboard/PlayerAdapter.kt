package com.example.twaran25.leaderboard

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.twaran25.R

class PlayerAdapter(private val playerList: List<PlayerInfo>

    ) :
    RecyclerView.Adapter<PlayerAdapter.GameViewHolder>() {

    class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image)
        val name: TextView = itemView.findViewById(R.id.name)
        val place: TextView = itemView.findViewById(R.id.place)
        val college: TextView = itemView.findViewById(R.id.college)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.player_info_design, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val player=playerList[position]
        holder.imageView.setImageResource(player.image)
        holder.name.text=player.name
        holder.place.text="${player.place}"
        holder.college.text=player.college

    }

    override fun getItemCount(): Int = playerList.size
}
