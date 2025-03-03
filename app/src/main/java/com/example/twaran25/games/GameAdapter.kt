package com.example.twaran25.games

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.twaran25.R

class GameAdapter(private val gameList: List<Game>,
    private val onSportClick : (Game) -> Unit,
    ) :
    RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val arrowButton: ImageButton = itemView.findViewById(R.id.arrow_btn)
        val textView: TextView = itemView.findViewById(R.id.title)
        val descriptionTextView: TextView = itemView.findViewById(R.id.description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.games_design_card, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = gameList[position]  // Get current game item

        holder.textView.text = game.title
        holder.descriptionTextView.text = game.description
        holder.imageView.setImageResource(game.image) // Set correct image

        holder.arrowButton.setOnClickListener {
           onSportClick(game)
        }
    }

    override fun getItemCount(): Int = gameList.size
}
