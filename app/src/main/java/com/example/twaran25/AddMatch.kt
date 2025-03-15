package com.example.twaran25

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.twaran25.data.models.Matches
import com.example.twaran25.data.viewmodel.MatchViewModel
import com.example.twaran25.databinding.ActivityAddMatchBinding
import java.util.Calendar

import java.util.UUID

class AddMatch : AppCompatActivity() {
    private lateinit var binding: ActivityAddMatchBinding
    private val matchViewModel: MatchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAddMatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.sportName.setOnClickListener { setupPopupMenu(it, binding.sportName) }
        binding.teamA.setOnClickListener { setupCollegePopupMenu(it, binding.teamA) }
        binding.teamB.setOnClickListener { setupCollegePopupMenu(it, binding.teamB) }
        binding.daySelector.setEndIconOnClickListener {
            showDatePicker()
        }
        binding.timeSelector.setEndIconOnClickListener {
            showTimePicker()
        }
        binding.btnSubmit.setOnClickListener {
            val match = Matches(
                matchId = UUID.randomUUID().toString(),
                sportsName = binding.sportName.text.toString(),
                teamA = binding.teamA.text.toString(),
                teamB = binding.teamB.text.toString(),
                day = binding.day.text.toString().toIntOrNull() ?: 0,
                time = binding.time.text.toString(),
                date = binding.date.text.toString(),
                venue = binding.venue.text.toString(),
                teamAScore = 0,
                teamBScore = 0,
                teamAResult = 0,
                teamBResult = 0
            )
            matchViewModel.addMatch(match)

            // Clear input fields
            binding.sportName.text?.clear()
            binding.teamA.text?.clear()
            binding.teamB.text?.clear()
            binding.day.text?.clear()
            binding.time.text?.clear()
            binding.date.text?.clear()
            binding.venue.text?.clear()
            finish()
        }
    }
    private fun setupPopupMenu(view: View, editText: android.widget.EditText) {
        val popupMenu = PopupMenu(view.context, view).apply {
            menuInflater.inflate(R.menu.sports_menu, menu)
            setOnMenuItemClickListener { item ->
                editText.setText(
                    when (item.itemId) {
                        R.id.menu_cricket -> "Cricket"
                        R.id.menu_football -> "Football"
                        R.id.menu_basketball -> "Basketball"
                        R.id.menu_tennis -> "Tennis"
                        R.id.menu_volleyball -> "Volleyball"
                        R.id.menu_boxing -> "Boxing"
                        R.id.menu_wrestling -> "Wrestling"
                        else -> ""
                    }
                )
                true
            }
        }
        popupMenu.show()
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
            colleges.forEachIndexed { index, college ->
                menu.add(0, index, 0, college)
            }
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
            binding.time.setText(formattedTime) // Set selected time
        }, hour, minute, true) // true = 24-hour format, false = 12-hour

        timePicker.show()
    }
    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val formattedDate = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)
            binding.day.setText(formattedDate) // Set selected date
        }, year, month, day)

        datePicker.show()
    }
}
