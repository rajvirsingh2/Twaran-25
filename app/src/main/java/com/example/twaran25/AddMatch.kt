package com.example.twaran25

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
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

        binding.sportName.setOnClickListener { setupSportsPopup(it, binding.sportName) }
        binding.sportType.setOnClickListener { setupSportTypePopup(it, binding.sportType) }
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
                sportsType = binding.sportType.text.toString(),
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
            binding.sportType.text?.clear() 
            binding.teamA.text?.clear()
            binding.teamB.text?.clear()
            binding.day.text?.clear()
            binding.time.text?.clear()
            binding.date.text?.clear()
            binding.venue.text?.clear()
            finish()
        }
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
        popupMenu.menu.add("Athletics Women")
        popupMenu.menu.add("Badminton Women")
        popupMenu.menu.add("Basketball Women")
        popupMenu.menu.add("Carrom Women")
        popupMenu.menu.add("Powerlifting Women")
        popupMenu.menu.add("Table Tennis Women")
        popupMenu.menu.add("Lawn Tennis Women")
        popupMenu.menu.add("Volleyball Women")
        popupMenu.menu.add("Squash Women")
        popupMenu.menu.add("Aquatics Women")
        popupMenu.menu.add("Tug of War Women")
        popupMenu.menu.add("Kabaddi Women")
        popupMenu.menu.add("Kho-Kho Women")

        // Combined sports (Men & Women Combined)
        popupMenu.menu.add("Chess Combined")

        // Handle menu item click
        popupMenu.setOnMenuItemClickListener { item ->
            editText.setText(item.title.toString())
            true
        }

        popupMenu.show()
    }
    private fun setupSportTypePopup(view: View, editText: EditText) {
        val popupMenu = PopupMenu(view.context, view)

        // Adding sport types to the menu
        popupMenu.menu.add("100m")
        popupMenu.menu.add("200m")
        popupMenu.menu.add("400m")
        popupMenu.menu.add("800m")
        popupMenu.menu.add("1500m")
        popupMenu.menu.add("5000m")
        popupMenu.menu.add("4x100m")
        popupMenu.menu.add("4x400m")
        popupMenu.menu.add("Javelin Throw")
        popupMenu.menu.add("Shotput")
        popupMenu.menu.add("Discuss Throw")
        popupMenu.menu.add("Long Jump")
        popupMenu.menu.add("High Jump")
        popupMenu.menu.add("Triple Jump")
        popupMenu.menu.add("Hurdles(100m)")
        popupMenu.menu.add("Freestyle")
        popupMenu.menu.add("BreastStroke")
        popupMenu.menu.add("BackStroke")
        popupMenu.menu.add("Medley (50*4)")
        popupMenu.menu.add("FreeStyle (50*4)")
        popupMenu.menu.add("Bench Press")
        popupMenu.menu.add("Deadlift")
        popupMenu.menu.add("Squats")

          // Handle menu item click
        popupMenu.setOnMenuItemClickListener { item ->
            editText.setText(item.title.toString())
            true
        }

        popupMenu.show()
    }



    private fun setupCollegePopupMenu(view: View, editText: android.widget.EditText) {
        val colleges = listOf(
            "All","IIIT Gwalior", "IIIT kurnool", "IIIT Allahabad", "IIIT Jabalpur", "IIIT Kancheepuram",
            "IIIT Guwahati", "IIIT Vadodara", "IIIT Kota", "IIIT Kalyani", "IIIT Una",
            "IIIT Sonepat", "IIIT Lucknow", "IIIT Dharwad", "IIIT Kottayam", "IIIT Manipur",
            "IIIT Tiruchirappalli", "IIIT Nagpur", "IIIT Pune", "IIIT Ranchi", "IIIT Bhagalpur",
            "IIIT Bhopal", "IIIT Agartala", "IIIT Raichur", "IIIT Surat", "IIIT Sricity"
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
            binding.date.setText(formattedDate) // Set selected date
        }, year, month, day)

        datePicker.show()
    }
}
