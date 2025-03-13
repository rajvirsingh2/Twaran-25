package com.example.twaran25.data.repository

import android.util.Log
import com.example.twaran25.data.models.Contact
import com.example.twaran25.data.models.LeaderboardEntry
import com.example.twaran25.data.models.Matches
import com.google.firebase.database.*

class FirebaseRepository {
    private val db = FirebaseDatabase.getInstance().reference

    //add contact to db
    fun contactRequest(contact: Contact) {
        db.child("contacts").child(contact.email).setValue(contact)
    }

    //fetch contacts from db
    fun fetchContact(onResult: (List<Contact>) -> Unit){
        db.child("contacts").addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val contact = snapshot.children.mapNotNull { it.getValue(Contact::class.java) }
                onResult(contact)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    //add matches to db
    fun addMatch(match: Matches) {
        db.child("matches").child(match.matchId).setValue(match)
    }

    //fetch matches from db
    fun fetchMatches(onResult: (List<Matches>) -> Unit) {
        db.child("matches").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val matchList = snapshot.children.mapNotNull { it.getValue(Matches::class.java) }
                onResult(matchList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseRepository", "Error occurred while fetching:${error.message}")
            }
        })
    }

    //fetch leaderboard info
    fun fetchLeaderboard(onResult: (List<LeaderboardEntry>) -> Unit) {
        db.child("leaderboard").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val leaderboard = snapshot.children.mapNotNull { it.getValue(LeaderboardEntry::class.java) }
                onResult(leaderboard)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseRepository", "Error occurred while fetching:${error.message}")
            }
        })
    }

    //update medals count
    fun updateMedalCount(entry: LeaderboardEntry) {
        val collegeReference = db.child("leaderboard").child(entry.collegeName)
        val updates = mapOf(
            "goldCount" to entry.goldCount,
            "silverCount" to entry.silverCount,
            "bronzeCount" to entry.bronzeCount,
            "points" to entry.points
        )
        collegeReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val currentEntry = snapshot.getValue(LeaderboardEntry::class.java)
                    currentEntry?.let {
                        val updatedEntry = mapOf(
                            "goldCount" to (it.goldCount + entry.goldCount),
                            "silverCount" to (it.silverCount + entry.silverCount),
                            "bronzeCount" to (it.bronzeCount + entry.bronzeCount),
                            "points" to (it.points + entry.points)
                        )
                        collegeReference.updateChildren(updatedEntry)
                    }
                } else {
                    db.child("leaderboard").child(entry.collegeName).updateChildren(updates)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseRepository", "Error: ${error.message}")
            }
        })
    }
}