package com.example.twaran25.events

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.twaran25.R

class GameMatchAdapter(private val matchList: List<Event>) :
    RecyclerView.Adapter<GameMatchAdapter.GameMatchViewHolder>() {

    class GameMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val startTime: TextView = view.findViewById(R.id.start_time)
        val gameName: TextView = view.findViewById(R.id.game_name)
        val date: TextView = view.findViewById(R.id.date)
        val place: TextView = view.findViewById(R.id.place)
        val collegeOne: TextView = view.findViewById(R.id.college_one)
        val collegeTwo: TextView = view.findViewById(R.id.college_two)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameMatchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_layout, parent, false)
        return GameMatchViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameMatchViewHolder, position: Int) {
        val match = matchList[position]
        holder.startTime.text = match.startTime
        holder.gameName.text = match.gameName
        holder.date.text = "Date: ${match.date}"
        holder.place.text = "Place: ${match.place}"
        holder.collegeOne.text = match.collegeOne
        holder.collegeTwo.text = match.collegeTwo
    }

    override fun getItemCount(): Int = matchList.size
}
