package com.example.twaran25

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.twaran25.data.models.Matches
import com.example.twaran25.data.viewmodel.MatchViewModel
import com.example.twaran25.databinding.ActivityAdminEditMatchBinding
import java.util.Calendar

class AdminEditMatch : AppCompatActivity() {
    private lateinit var binding: ActivityAdminEditMatchBinding
    private val viewModel: MatchViewModel by viewModels()
    private var matchId: String? = null
    private var currentMatch: Matches? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminEditMatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        matchId = intent.getStringExtra("matchId")

        matchId?.let {
            viewModel.fetchMatch(it)
            observeMatchData()
        } ?: run {
            Toast.makeText(this, "Match ID not found!", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.sportName.setOnClickListener { setupSportsPopup(it, binding.sportName) }
        binding.teamA.setOnClickListener { setupCollegePopupMenu(it, binding.teamA) }
        binding.teamB.setOnClickListener { setupCollegePopupMenu(it, binding.teamB) }
        binding.daySelector.setEndIconOnClickListener { showDatePicker() }
        binding.timeSelector.setEndIconOnClickListener { showTimePicker() }

        binding.btnSubmit.setOnClickListener {
            updateMatchDetails()
            finish()
        }
    }

    private fun observeMatchData() {
        viewModel.match.observe(this, Observer { match ->
            match?.let {
                currentMatch = it
                binding.sportName.setText(it.sportsName)
                binding.teamA.setText(it.teamA)
                binding.teamB.setText(it.teamB)
                binding.day.setText(it.day.toString())
                binding.date.setText(it.date)
                binding.time.setText(it.time)
                binding.venue.setText(it.venue)
                binding.teamAScore.setText(it.teamAScore.toString())
                binding.teamBScore.setText(it.teamBScore.toString())
                binding.teamAResult.setText(it.teamAResult.toString())
                binding.teamBResult.setText(it.teamBResult.toString())
            }
        })
    }

    private fun setupSportsPopup(view: View, editText: android.widget.EditText) {
        val popupMenu = PopupMenu(view.context, view)

        // Men's sports
        popupMenu.menu.add("Athletics")
        popupMenu.menu.add("Badminton")
        popupMenu.menu.add("Basketball")
        popupMenu.menu.add("Carrom")
        popupMenu.menu.add("Cricket")
        popupMenu.menu.add("Kabaddi")
        popupMenu.menu.add("Football")
        popupMenu.menu.add("Powerlifting")
        popupMenu.menu.add("Table Tennis")
        popupMenu.menu.add("Lawn Tennis")
        popupMenu.menu.add("Volleyball")
        popupMenu.menu.add("Squash")
        popupMenu.menu.add("Aquatics")
        popupMenu.menu.add("Tug of War")
        popupMenu.menu.add("Kho-Kho")

        // Women's sports (with "(Women)" suffix)
        popupMenu.menu.add("Athletics (Women)")
        popupMenu.menu.add("Badminton (Women)")
        popupMenu.menu.add("Basketball (Women)")
        popupMenu.menu.add("Carrom (Women)")
        popupMenu.menu.add("Powerlifting (Women)")
        popupMenu.menu.add("Table Tennis (Women)")
        popupMenu.menu.add("Lawn Tennis (Women)")
        popupMenu.menu.add("Volleyball (Women)")
        popupMenu.menu.add("Squash (Women)")
        popupMenu.menu.add("Aquatics (Women)")
        popupMenu.menu.add("Tug of War (Women)")
        popupMenu.menu.add("Kabaddi (Women)")
        popupMenu.menu.add("Kho-Kho (Women)")

        // Combined sports (Men & Women Combined)
        popupMenu.menu.add("Chess (Men & Women Combined)")

        // Handle menu item click
        popupMenu.setOnMenuItemClickListener { item ->
            editText.setText(item.title.toString())
            true
        }

        popupMenu.show()
    }
    private fun updateMatchDetails() {
        if (currentMatch == null) return

        val updatedMatch = currentMatch!!.copy(
            sportsName = binding.sportName.text.toString(),
            teamA = binding.teamA.text.toString(),
            teamB = binding.teamB.text.toString(),
            date = binding.day.text.toString(),
            time = binding.time.text.toString(),
            venue = binding.venue.text.toString(),
            teamAScore = binding.teamAScore.text.toString().toIntOrNull() ?: 0,
            teamBScore = binding.teamBScore.text.toString().toIntOrNull() ?: 0,
            teamAResult = binding.teamAScore.text.toString().toIntOrNull() ?: 0,
            teamBResult = binding.teamBScore.text.toString().toIntOrNull() ?: 0,
        )

        viewModel.addMatch(updatedMatch)
        Toast.makeText(this, "Match updated successfully", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun setupCollegePopupMenu(view: View, editText: android.widget.EditText) {
        val colleges = listOf(
            "IIIT Gwalior", "IIIT Hyderabad", "IIIT Allahabad", "IIIT Jabalpur", "IIIT Kancheepuram",
            "IIIT Guwahati", "IIIT Vadodara", "IIIT Kota", "IIIT Kalyani", "IIIT Una",
            "IIIT Sonepat", "IIIT Lucknow", "IIIT Dharwad", "IIIT Kottayam", "IIIT Manipur",
            "IIIT Tiruchirappalli", "IIIT Nagpur", "IIIT Pune", "IIIT Ranchi", "IIIT Bhagalpur",
            "IIIT Bhopal", "IIIT Agartala", "IIIT Raichur", "IIIT Surat"
        )

        val popupMenu = PopupMenu(view.context, view).apply {
            colleges.forEachIndexed { index, college -> menu.add(0, index, 0, college) }
            setOnMenuItemClickListener { item ->
                editText.setText(colleges[item.itemId])
                true
            }
        }
        popupMenu.show()
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePicker = TimePickerDialog(this, { _, selectedHour, selectedMinute ->
            val formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
            binding.time.setText(formattedTime)
        }, hour, minute, true)

        timePicker.show()
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val formattedDate = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)
            binding.day.setText(formattedDate)
        }, year, month, day)

        datePicker.show()
    }
}
