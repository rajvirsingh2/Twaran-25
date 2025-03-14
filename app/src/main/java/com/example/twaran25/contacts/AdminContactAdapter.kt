package com.example.twaran25.contacts

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.twaran25.R

// Ensure you import the correct activity
import com.example.twaran25.contacts.Insankiaas

// Data class for sports
data class SportItem(val name: String)

// Adapter class
class AdminContactAdapter(
    private val context: Context, // Pass an Activity context
    private val sportsList: List<SportItem>
) : RecyclerView.Adapter<AdminContactAdapter.SportViewHolder>() {

    inner class SportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sportName: TextView = itemView.findViewById(R.id.student_name)
        val forwardButton: View = itemView.findViewById(R.id.forward_button) // Add this in XML

        init {
            val validContext = context as? Activity ?: itemView.context

            itemView.setOnClickListener {
                openNewActivity(validContext, adapterPosition)
            }

            forwardButton.setOnClickListener {
                openNewActivity(validContext, adapterPosition)
            }
        }

        private fun openNewActivity(context: Context, position: Int) {
            if (position != RecyclerView.NO_POSITION) {
                val sport = sportsList[position]
                val intent = Intent(context, Insankiaas::class.java).apply {
                    putExtra("SPORT_NAME", sport.name) // Pass data
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.admincontact, parent, false)
        return SportViewHolder(view)
    }

    override fun onBindViewHolder(holder: SportViewHolder, position: Int) {
        val sport = sportsList[position]
        holder.sportName.text = sport.name
    }

    override fun getItemCount(): Int = sportsList.size
}
