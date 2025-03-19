package com.example.twaran25.leaderboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.twaran25.R
import com.example.twaran25.data.models.LeaderboardEntry

class AdminLeaderboardAdapter(
    private val onItemClick: (LeaderboardEntry) -> Unit // Callback for item clicks
) : RecyclerView.Adapter<AdminLeaderboardAdapter.LeaderboardViewHolder>() {

    private val leaderboardList = mutableListOf<LeaderboardEntry>()

    fun updateData(newList: List<LeaderboardEntry>) {
        leaderboardList.clear()
        leaderboardList.addAll(newList.sortedByDescending { it.points }) // Sort by points
        notifyDataSetChanged()
    }

    private val mappedEntries = mapOf(
        "All" to R.drawable.college,
        "IIIT Gwalior" to R.drawable.iiitgwalior,
        "IIIT Una" to R.drawable.iiituna,
        "IIIT Kota" to R.drawable.iiitkota,
        "IIIT Pune" to R.drawable.iiitpune,
        "IIIT Surat" to R.drawable.iiitsurat,
        "IIIT Kalyani" to R.drawable.iiit_kalyani,
        "IIIT Agartala" to R.drawable.iiitagartala,
        "IIIT Allahabad" to R.drawable.iiitallahabad,
        "IIIT Bhagalpur" to R.drawable.iiitbhagalpur,
        "IIIT Bhopal" to R.drawable.iiitbhopal,
        "IIIT Dharwad" to R.drawable.iiitdharwad,
        "IIIT Guwahati" to R.drawable.iiitguwahati,
        "IIIT kurnool" to R.drawable.iiitkurnool,
        "IIIT Jabalpur" to R.drawable.iiitjabalpur,
        "IIIT Kancheepuram" to R.drawable.iiitkancheepuram,
        "IIIT Kottayam" to R.drawable.iiitkottatam,
        "IIIT Lucknow" to R.drawable.iiitlucknow,
        "IIIT Manipur" to R.drawable.iiitmanipur,
        "IIIT Nagpur" to R.drawable.iiitnagpur,
        "IIIT Raichur" to R.drawable.iiitraichur,
        "IIIT Ranchi" to R.drawable.iiitranchi,
        "IIIT Sonepat" to R.drawable.iiitsonepat,
        "IIIT Tiruchirappalli" to R.drawable.iiittiruchirappalli,
        "IIIT Vadodara" to R.drawable.iiitvadodra
    )

    private fun getImageResource(collegeName: String): Int {
        return mappedEntries[collegeName] ?: R.drawable.iiitgwalior
    }

    inner class LeaderboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.image)
        private val name: TextView = itemView.findViewById(R.id.name)
        private val goldText: TextView = itemView.findViewById(R.id.gold_amount)
        private val silverText: TextView = itemView.findViewById(R.id.silver_amount)
        private val bronzeText: TextView = itemView.findViewById(R.id.brnz_amount)
        private val positionText: TextView = itemView.findViewById(R.id.position)

        fun bind(entry: LeaderboardEntry, position: Int) {
            name.text = entry.collegeName
            imageView.setImageResource(getImageResource(entry.collegeName))
            goldText.text = entry.goldCount.toString()
            silverText.text = entry.silverCount.toString()
            bronzeText.text = entry.bronzeCount.toString()
            positionText.text = (position + 1).toString()

            itemView.setOnClickListener {
                onItemClick(entry) // Handle item click
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.player_info_design, parent, false)
        return LeaderboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        val entry = leaderboardList[position]
        holder.bind(entry, position)
    }

    override fun getItemCount(): Int = leaderboardList.size
}
