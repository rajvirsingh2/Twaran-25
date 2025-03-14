package com.example.twaran25.events

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.twaran25.R

class GameMatchAdapter(private val matchList: List<Event>) :
    RecyclerView.Adapter<GameMatchAdapter.GameMatchViewHolder>() {

    val mappedEntries = mapOf(
        R.drawable.iiitgwalior to "IIIT Gwalior",
        R.drawable.iiituna to "IIIT Una",
        R.drawable.iiitkota to "IIIT Kota",
        R.drawable.iiitpune to "IIIT Pune",
        R.drawable.iiitsurat to "IIIT Surat",
        R.drawable.iiit_kalyani to "IIIT Kalyani",
        R.drawable.iiitagartala to "IIIT Agartala",
        R.drawable.iiitallahabad to "IIIT Allahabad",
        R.drawable.iiitbhagalpur to "IIIT Bhagalpur",
        R.drawable.iiitbhopal to "IIIT Bhopal",
        R.drawable.iiitdharwad to "IIIT Dharwad",
        R.drawable.iiitguwahati to "IIIT Guwahati",
        R.drawable.iiithyderabad to "IIIT Hyderabad",
        R.drawable.iiitjabalpur to "IIIT Jabalpur",
        R.drawable.iiitkancheepuram to "IIIT Kancheepuram",
        R.drawable.iiitkottatam to "IIIT Kottayam",
        R.drawable.iiitlucknow to "IIIT Lucknow",
        R.drawable.iiitmanipur to "IIIT Manipur",
        R.drawable.iiitnagpur to "IIIT Nagpur",
        R.drawable.iiitraichur to "IIIT Raichur",
        R.drawable.iiitranchi to "IIIT Ranchi",
        R.drawable.iiitsonepat to "IIIT Sonepat",
        R.drawable.iiittiruchirappalli to "IIIT Tiruchirappalli",
        R.drawable.iiitvadodra to "IIIT Vadodara"
    )

    fun imageMatch(collegeName: String): Int?{
        return mappedEntries.entries.find { it.value == collegeName }?.key
    }

    class GameMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val startTime: TextView = view.findViewById(R.id.start_time)
        val gameName: TextView = view.findViewById(R.id.game_name)
        val date: TextView = view.findViewById(R.id.date)
        val place: TextView = view.findViewById(R.id.place)
        val collegeOne: TextView = view.findViewById(R.id.college_one)
        val collegeTwo: TextView = view.findViewById(R.id.college_two)
        val verticalLine: View = view.findViewById(R.id.vertical_line) // Added reference to vertical line
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

        // Hide vertical line for the last item
        if (position == matchList.size - 1) {
            holder.verticalLine.visibility = View.GONE
        } else {
            holder.verticalLine.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int = matchList.size
}
