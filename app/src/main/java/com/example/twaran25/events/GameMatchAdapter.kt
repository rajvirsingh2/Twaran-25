package com.example.twaran25.events

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.twaran25.R
import com.example.twaran25.data.models.Matches
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class GameMatchAdapter(private var matchList: MutableList<Matches>) :
    RecyclerView.Adapter<GameMatchAdapter.GameMatchViewHolder>() {

    private val TAG = "GameMatchAdapter"
    private val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    private val currentTime = Calendar.getInstance().time

    fun updateData(newList: List<Matches>) {
        Log.d(TAG, "Updating adapter data: ${newList.size} matches received")

        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

        val sortedList = newList.sortedWith(compareBy { match ->
            try {
                val dateTimeString = "${match.date.trim()} ${match.time.trim()}"
                dateFormat.parse(dateTimeString) ?: Date() // Default to current date if parsing fails
            } catch (e: ParseException) {
                Log.e(TAG, "Error parsing date/time for match: ${match.date} ${match.time}", e)
                Date() // Default fallback
            }
        })

        matchList.clear()
        matchList.addAll(sortedList)
        notifyDataSetChanged()
    }


    private val mappedEntries = mapOf(
        R.drawable.iiitsricity to "IIIT Sricity",
        R.drawable.iiitgwalior to "IIIT Gwalior",
        R.drawable.college to "All",
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
        R.drawable.iiitkurnool to "IIIT kurnool",
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

    private fun getImageResource(collegeName: String): Int? {
        return mappedEntries.entries.find { it.value == collegeName }?.key
    }

    class GameMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val startTime: TextView = view.findViewById(R.id.start_time)
        val gameName: TextView = view.findViewById(R.id.game_name)
        val sportsType: TextView = view.findViewById(R.id.game_type)
        val date: TextView = view.findViewById(R.id.date)
        val place: TextView = view.findViewById(R.id.place)
        val collegeOne: TextView = view.findViewById(R.id.college_one)
        val collegeTwo: TextView = view.findViewById(R.id.college_two)
        val logoOne: ImageView = view.findViewById(R.id.college_one_image)
        val logoTwo: ImageView = view.findViewById(R.id.college_two_image)
        val verticalLine: View = view.findViewById(R.id.vertical_line)
        val dot: View = view.findViewById(R.id.dot)
        val score: TextView = view.findViewById(R.id.score)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameMatchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_layout, parent, false)
        return GameMatchViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameMatchViewHolder, position: Int) {
        val match = matchList[position]
        Log.d(TAG, "Binding match at position $position: ${match.sportsName} (${match.teamA} vs ${match.teamB})")

        holder.startTime.text = match.time
        holder.gameName.text = match.sportsName
        holder.date.text = match.date
        holder.place.text = match.venue
        holder.collegeOne.text = match.teamA
        holder.collegeTwo.text = match.teamB

        holder.score.text = if (match.teamAScore != 0 || match.teamBScore != 0) {
            "${match.teamAScore} - ${match.teamBScore}"
        } else {
            "V/S"
        }

        val context = holder.itemView.context
        holder.collegeOne.setTextColor(
            when (match.teamAResult) {
                1 -> ContextCompat.getColor(context, R.color.green)
                -1 -> ContextCompat.getColor(context, R.color.red)
                else -> ContextCompat.getColor(context, R.color.white)
            }
        )

        holder.collegeTwo.setTextColor(
            when (match.teamBResult) {
                1 -> ContextCompat.getColor(context, R.color.green)
                -1 -> ContextCompat.getColor(context, R.color.red)
                else -> ContextCompat.getColor(context, R.color.white)
            }
        )
        if (match.sportsName.equals("Aquatics", ignoreCase = true) ||
        match.sportsName.equals("Athletics", ignoreCase = true) ||
        match.sportsName.equals("Powerlifting", ignoreCase = true || match.sportsName.equals("Athletics Women" , ignoreCase = true) || match.sportsName.equals("Aquatics Women", ignoreCase = true) || match.sportsName.equals("Powerlifting Women", ignoreCase = true) )) {
        
        // Make the sportsType TextView visible
        holder.sportsType.visibility = View.VISIBLE
        holder.sportsType.text = match.sportsType  // Set the sportsType text (you should ensure match has this field)
    } else {
        // Hide the sportsType TextView if not applicable
        holder.sportsType.visibility = View.INVISIBLE
    }

        try {
            val matchTime = dateFormat.parse(match.time)
            val matchDate = dateFormat.parse(match.date)
            val isPastMatch = matchTime?.before(currentTime) == true
            val isPastMatchDate = matchTime?.before(matchDate) == true
            holder.verticalLine.setBackgroundColor(if (isPastMatch && isPastMatchDate) Color.RED else Color.WHITE)
            (if (isPastMatch && isPastMatchDate) holder.dot.setBackgroundResource(R.drawable.redcircle) else holder.dot.setBackgroundResource(R.drawable.circular_shape))
            (if (isPastMatch && isPastMatchDate) holder.startTime.setTextColor(Color.RED) else holder.startTime.setTextColor(Color.WHITE))

        } catch (e: Exception) {
            Log.e(TAG, "Error parsing match time: ${match.time}", e)
        }

        holder.logoOne.setImageResource(getImageResource(match.teamA) ?: R.drawable.college)
        holder.logoTwo.setImageResource(getImageResource(match.teamB) ?: R.drawable.college)

        holder.verticalLine.visibility = if (position == matchList.size - 1) View.GONE else View.VISIBLE
    }

    override fun getItemCount(): Int = matchList.size
}
