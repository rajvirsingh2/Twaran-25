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
import com.example.twaran25.data.models.Contact

class AdminContactAdapter(
    private val context: Context,
    private var contactList: List<Contact>
) : RecyclerView.Adapter<AdminContactAdapter.SportViewHolder>() {

    inner class SportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sportName: TextView = itemView.findViewById(R.id.student_name)
        val forwardButton: View = itemView.findViewById(R.id.forward_button)

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
                val contact = contactList[position]
                val intent = Intent(context, Insankiaas::class.java).apply {
                    putExtra("query_id", contact.id)
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
        val contact = contactList[position]
        holder.sportName.text = contact.name
    }

    override fun getItemCount(): Int = contactList.size

    fun updateContacts(newContacts: List<Contact>) {
        contactList = newContacts
        notifyDataSetChanged()
    }
}
