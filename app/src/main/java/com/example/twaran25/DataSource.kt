package com.example.twaran25


import com.example.twaran25.events.Event
import com.example.twaran25.games.Game

object DataSource {
    val colleges = listOf(
        College(1, "IIIT Gwalior", R.drawable.iiitgwalior),
        College(2, "IIIT Hyderabad", R.drawable.iiithyderabad),
        College(3, "IIIT Allahabad", R.drawable.iiitallahabad),
        College(4, "IIIT Jabalpur", R.drawable.iiitjabalpur),
        College(5, "IIIT Kancheepuram", R.drawable.iiitkancheepuram),
        College(6, "IIIT Guwahati", R.drawable.iiitguwahati),
        College(7, "IIIT Vadodara", R.drawable.iiitvadodra),
        College(8, "IIIT Kota", R.drawable.iiitkota),
        College(9, "IIIT Kalyani", R.drawable.iiit_kalyani),
        College(10, "IIIT Una", R.drawable.iiituna),
        College(11, "IIIT Sonepat", R.drawable.iiitsonepat),
        College(12, "IIIT Lucknow", R.drawable.iiitlucknow),
        College(13, "IIIT Dharwad", R.drawable.iiitdharwad),
        College(14, "IIIT Kottayam", R.drawable.iiitkottatam),
        College(15, "IIIT Manipur", R.drawable.iiitmanipur),
        College(16, "IIIT Tiruchirappalli", R.drawable.iiittiruchirappalli),
        College(17, "IIIT Nagpur", R.drawable.iiitnagpur),
        College(18, "IIIT Pune", R.drawable.iiitpune),
        College(19, "IIIT Ranchi", R.drawable.iiitranchi),
        College(20, "IIIT Bhagalpur", R.drawable.iiitbhagalpur),
        College(21, "IIIT Bhopal", R.drawable.iiitbhopal),
        College(22, "IIIT Agartala", R.drawable.iiitagartala),
        College(23, "IIIT Raichur", R.drawable.iiitraichur),
        College(24, "IIIT Surat", R.drawable.iiitsurat)
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
            "10:00 AM", "Basketball", "05.03.2025", "Old Ground", "IIIT Hyderabad", "IIIT Bangalore"
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