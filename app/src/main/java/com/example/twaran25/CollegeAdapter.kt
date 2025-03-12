package com.example.twaran25

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CollegeAdapter(private val colleges: List<College>,private val onCollegeIconClick: (String) -> Unit) :
    RecyclerView.Adapter<CollegeAdapter.CollegeViewHolder>() {

    class CollegeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageButton: ImageButton = itemView.findViewById(R.id.college_list_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollegeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.college_list, parent, false)
        return CollegeViewHolder(view)
    }

    override fun onBindViewHolder(holder: CollegeViewHolder, position: Int) {
        holder.imageButton.setImageResource(colleges[position].imageRes)

        holder.imageButton.setOnClickListener {
            onCollegeIconClick(colleges[position].name)
        }
    }

    override fun getItemCount(): Int = colleges.size
}
