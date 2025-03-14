package com.example.twaran25

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupMenu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.twaran25.databinding.ActivityAdminEditMatchBinding
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

class AdminEditMatch : AppCompatActivity() {
    lateinit var binding: ActivityAdminEditMatchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAdminEditMatchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


// Usage for both Team A and Team B
        binding.teamASelector.setEndIconOnClickListener { view ->
            setupPopupMenu(view, binding.teamA)
        }

        binding.teamBSelector.setEndIconOnClickListener { view ->
            setupPopupMenu(view, binding.teamB)
        }
        binding.daySelector.setEndIconOnClickListener {
            showDatePicker()
        }
        binding.timeSelector.setEndIconOnClickListener {
            showTimePicker()
        }



    }
    private fun setupPopupMenu(view: View, editText: TextInputEditText) {
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
