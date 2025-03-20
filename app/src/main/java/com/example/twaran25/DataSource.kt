package com.example.twaran25


import com.example.twaran25.events.Event
import com.example.twaran25.games.Game

object DataSource {
    val colleges = listOf(
        College(1, "IIIT Gwalior", R.drawable.iiitgwalior),
        College(2, "IIIT kurnool", R.drawable.iiitkurnool),
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
        College(24, "IIIT Surat", R.drawable.iiitsurat),
        College(25, "IIIT Sricity", R.drawable.iiitsricity)
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
            "10:00 AM", "Basketball", "05.03.2025", "Old Ground", "IIIT kurnool", "IIIT Bangalore"
        ),
        Event("2:00 PM", "Cricket", "06.03.2025", "Main Stadium", "IIIT Allahabad", "IIIT Pune")
    )
    val mensSports = listOf(
        Game("Athletics", "Run fast, jump high, break limits!", R.drawable.atheletic),
        Game("Badminton", "Swift moves, sharp smashes!", R.drawable.badminton),
        Game("Basketball", "Dribble, shoot, dominate!", R.drawable.basketball),
        Game("Carrom", "Strike, pocket, and win!", R.drawable.carrom),
        Game("Cricket", "Smash boundaries, seize glory!", R.drawable.cricket),
        Game("Kabaddi", "Tag fast, defend strong!", R.drawable.kabaddi),
        Game("Football", "Kick hard, play smart, win big!", R.drawable.football),
        Game("Powerlifting", "Lift heavy, stay strong!", R.drawable.powerlifting),
        Game("Table Tennis", "Fast hands, quick wins!", R.drawable.table_tennis),
        Game("Lawn Tennis", "Serve, smash, and conquer!", R.drawable.tennis),
        Game("Volleyball", "Set high, spike hard!", R.drawable.vollyball),
        Game("Squash", "Hit hard, move fast!", R.drawable.squash),
        Game("Aquatics", "Make a splash, chase the dash!", R.drawable.swimming),
        Game("Tug of War", "Pull hard, win strong!", R.drawable.tug_of_war),
        Game("Kho-Kho", "Run, dodge, and tag fast!", R.drawable.kho_kho)
    )

    val womenSports = listOf(
        Game("Athletics Women", "Run fast, jump high, break limits!", R.drawable.atheletic),
        Game("Badminton Women", "Swift moves, sharp smashes!", R.drawable.badminton),
        Game("Basketball Women", "Dribble, shoot, dominate!", R.drawable.basketball),
        Game("Carrom Women", "Strike, pocket, and win!", R.drawable.carrom),
        Game("Powerlifting Women", "Lift heavy, stay strong!", R.drawable.powerlifting),
        Game("Table Tennis Women", "Fast hands, quick wins!", R.drawable.table_tennis),
        Game("Lawn Tennis Women", "Serve, smash, and conquer!", R.drawable.tennis),
        Game("Volleyball Women", "Set high, spike hard!", R.drawable.vollyball),
        Game("Squash Women", "Hit hard, move fast!", R.drawable.squash),
        Game("Aquatics Women", "Make a splash, chase the dash!", R.drawable.swimming),
        Game("Tug of War Women", "Pull hard, win strong!", R.drawable.tug_of_war),
        Game("Kabaddi Women", "Tag fast, defend strong!", R.drawable.kabaddi),
        Game("Kho-Kho Women", "Run, dodge, and tag fast!", R.drawable.kho_kho)
    )

    val combinedSports = listOf(
        Game("Chess Combined", "Think deep, move wisely!", R.drawable.chess)
    )


}