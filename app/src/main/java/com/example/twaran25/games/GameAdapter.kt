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
import com.example.twaran25.events.GameEventsActivity


class GameAdapter(
    private val fullGameList: List<Game>,   // Store original list
    private val onSportClick: (String) -> Unit
) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    private var filteredGameList: MutableList<Game> = fullGameList.toMutableList() // List that updates dynamically

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
        val game = filteredGameList[position]  // Get current filtered game

        holder.textView.text = game.title
        holder.descriptionTextView.text = game.description
        holder.imageView.setImageResource(game.image) // Set correct image

        holder.arrowButton.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, GameEventsActivity::class.java) // Replace with your target activity
            intent.putExtra("SPORT_NAME", game.title) // Pass sport name via intent
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = filteredGameList.size

    // 🔹 FILTER FUNCTION 🔹
    fun filter(query: String) {
        filteredGameList.clear()
        if (query.isEmpty()) {
            filteredGameList.addAll(fullGameList)
        } else {
            val lowerCaseQuery = query.lowercase()
            for (game in fullGameList) {
                if (game.title.lowercase().contains(lowerCaseQuery)) {
                    filteredGameList.add(game)
                }
            }
        }
        notifyDataSetChanged() // Refresh RecyclerView
    }
}
