package com.example.twaran25

import com.example.twaran25.events.Event
import com.example.twaran25.games.Game

object DataSource {
    val colleges = listOf(
        College(1, "IIIT Gwalior", R.drawable.iiitgwalior),
        College(2, "IIIT Guwahati", R.drawable.iiitguwahati),
        College(3, "IIIT Nagpur", R.drawable.iiitnagpur),
        College(4, "IIIT Raichur", R.drawable.iiitraichur),
        College(5, "IIIT Dharwad", R.drawable.iiitdharwad),
        College(6, "IIIT Vadodara", R.drawable.iiitvadodra),
        College(7, "IIIT Hyderabad", R.drawable.iiithyderabad),
        College(11, "IIIT Pune", R.drawable.iiitpune),
        College(22, "IIIT Agartala", R.drawable.iiitagartala),

    )

    val events = listOf(
        Event("8:00 AM", "Football", "05.03.2025", "New Ground", "IIIT Gwalior", "IIIT Delhi"),
        Event("8:00 AM", "Football", "05.03.2025", "New Ground", "IIIT Gwalior", "IIIT Delhi"),
        Event("8:00 AM", "Football", "05.03.2025", "New Ground", "IIIT Gwalior", "IIIT Delhi"),
        Event("8:00 AM", "Football", "05.03.2025", "New Ground", "IIIT Gwalior", "IIIT Delhi"),
        Event("8:00 AM", "Football", "05.03.2025", "New Ground", "IIIT Gwalior", "IIIT Delhi"),
        Event("8:00 AM", "Football", "05.03.2025", "New Ground", "IIIT Gwalior", "IIIT Delhi"),
        Event("8:00 AM", "Football", "05.03.2025", "New Ground", "IIIT Gwalior", "IIIT Delhi"),
        Event("8:00 AM", "Football", "05.03.2025", "New Ground", "IIIT Gwalior", "IIIT Delhi"),
        Event("8:00 AM", "Football", "05.03.2025", "New Ground", "IIIT Gwalior", "IIIT Delhi"),
        Event("8:00 AM", "Football", "05.03.2025", "New Ground", "IIIT Gwalior", "IIIT Delhi"),
        Event(
            "10:00 AM",
            "Basketball",
            "05.03.2025",
            "Old Ground",
            "IIIT Hyderabad",
            "IIIT Bangalore"
        ),
        Event("2:00 PM", "Cricket", "06.03.2025", "Main Stadium", "IIIT Allahabad", "IIIT Pune")
    )
     val mensSports = listOf(
        Game("Swimming", "Make a splash, chase the dash!", R.drawable.swimming),
        Game("Football", "Kick hard, play smart, win big!", R.drawable.football),
        Game("Athletics", "Run fast, jump high, break limits!", R.drawable.atheletic),
        Game("Cricket", "Smash boundaries, seize glory!", R.drawable.cricket),
        Game("Tennis", "Serve, smash, and conquer!", R.drawable.tennis),
        Game("Table Tennis", "Fast hands, quick wins!", R.drawable.table_tennis),
    )

     val womenSports = listOf(
        Game("Women Swimming", "Make a splash, chase the dash!", R.drawable.swimming),
        Game("Women Football", "Kick hard, play smart, win big!", R.drawable.football),
        Game("Women Athletics", "Run fast, jump high, break limits!", R.drawable.atheletic),
        Game("Women Cricket", "Smash boundaries, seize glory!", R.drawable.cricket),
        Game("Women Tennis", "Serve, smash, and conquer!", R.drawable.tennis),
        Game("Women Table Tennis", "Fast hands, quick wins!", R.drawable.table_tennis),
    )
}