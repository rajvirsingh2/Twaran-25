package com.example.twaran25.data.repository

import android.util.Log
import android.widget.Toast
import com.example.twaran25.data.models.Contact
import com.example.twaran25.data.models.LeaderboardEntry
import com.example.twaran25.data.models.Matches
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class FirebaseRepository {
    private val db = FirebaseDatabase.getInstance().reference

    //add contact to db
    fun contactRequest(contact: Contact) {
        contact.id.takeIf { it.isNotEmpty() }?.let { id ->
            db.child("contacts").child(id).setValue(contact)
        }
    }

    fun generateUniqueId(): String? {
        return db.child("contacts").push().key  // Generate unique ID
    }

    //fetch contacts from db
    fun fetchContact(onResult: (List<Contact>) -> Unit) {
        db.child("contacts").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val contacts = snapshot.children.mapNotNull {
                    val contact = it.getValue(Contact::class.java)
                    contact?.takeIf { it.id.isNotEmpty() }
                }
                onResult(contacts)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
    fun fetchContact(contactId: String, onResult: (Contact?) -> Unit) {
        db.child("contacts").child(contactId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val contact = snapshot.getValue(Contact::class.java)
                onResult(contact)
            }

            override fun onCancelled(error: DatabaseError) {
                onResult(null) // Return null if the fetch fails
            }
        })
    }


    //add matches to db
    fun addMatch(match: Matches) {
        db.child("matches").child(match.matchId).setValue(match)
    }

    fun addLeaderboard(entry: LeaderboardEntry) {
        db.child("leaderboard").child(entry.collegeName).setValue(entry)
    }

    fun fetchMatch(matchId: String, onResult: (Matches?) -> Unit) {
        db.child("matches").child(matchId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val match = snapshot.getValue(Matches::class.java)
                    onResult(match) // Returning a single match instead of a list
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("MatchRepository", "Error fetching match: ${error.message}")
                    onResult(null)
                }
            })
    }
    fun fetchLeaderboardEntry(collegeName: String, onResult: (LeaderboardEntry?) -> Unit) {
        Log.d("FirebaseRepository", "Fetching leaderboard entry for: $collegeName")

        db.child("leaderboard").child(collegeName)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val leaderboardEntry = snapshot.getValue(LeaderboardEntry::class.java)
                        Log.d("FirebaseRepository", "Fetched: $leaderboardEntry")
                        onResult(leaderboardEntry)
                    } else {
                        Log.e("FirebaseRepository", "No data found for: $collegeName")
                        onResult(null)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("FirebaseRepository", "Firebase error: ${error.message}")
                    onResult(null)
                }
            })
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

    fun fetchMatchesBySport(sportName: String, onResult: (List<Matches>) -> Unit) {
        db.child("matches").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val matchList = snapshot.children.mapNotNull { matchSnapshot ->
                    val match = matchSnapshot.getValue(Matches::class.java)
                    if (match?.sportsName == sportName) match else null
                }

                Log.d(
                    "FirebaseRepository",
                    "Fetched ${matchList.size} matches for sport: $sportName"
                )
                onResult(matchList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseRepository", "Error fetching matches: ${error.message}")
            }
        })
    }


    // Fetch matches by Day
    fun fetchMatchesByDay(day: Int, onResult: (List<Matches>) -> Unit) {
        db.child("matches").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val matchList = snapshot.children.mapNotNull {
                    it.getValue(Matches::class.java)
                }.filter { it.day == day }

                Log.d("FirebaseRepository", "Fetched ${matchList.size} matches for Day $day")
                onResult(matchList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseRepository", "Error fetching matches by day: ${error.message}")
            }
        })
    }

    // Fetch matches by Team
    fun fetchMatchesByTeam(teamName: String, onResult: (List<Matches>) -> Unit) {
        db.child("matches").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val matchList = snapshot.children.mapNotNull {
                    it.getValue(Matches::class.java)
                }.filter { it.teamA == teamName || it.teamB == teamName }

                Log.d("FirebaseRepository", "Fetched ${matchList.size} matches for team: $teamName")
                onResult(matchList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseRepository", "Error fetching matches by team: ${error.message}")
            }
        })
    }

    fun deleteContact(contactId: String, onResult: (Boolean) -> Unit) {
        db.child("contacts").child(contactId).removeValue()
            .addOnSuccessListener {
                Log.d("FirebaseRepository", "Query deleted successfully.")
                onResult(true)
            }
            .addOnFailureListener { error ->
                Log.e("FirebaseRepository", "Error deleting query: ${error.message}")
                onResult(false)
            }
    }


    // Fetch matches by Team and Day
    fun fetchMatchesByTeamAndDay(teamName: String, day: Int, onResult: (List<Matches>) -> Unit) {
        db.child("matches").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val matchList = snapshot.children.mapNotNull {
                    it.getValue(Matches::class.java)
                }.filter { (it.teamA == teamName || it.teamB == teamName) && it.day == day }

                Log.d(
                    "FirebaseRepository",
                    "Fetched ${matchList.size} matches for team: $teamName on Day $day"
                )
                onResult(matchList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(
                    "FirebaseRepository",
                    "Error fetching matches by team and day: ${error.message}"
                )
            }
        })
    }

    // Fetch matches by Sport and Day
    fun fetchMatchesBySportAndDay(sportName: String, day: Int, onResult: (List<Matches>) -> Unit) {
        db.child("matches").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val matchList = snapshot.children.mapNotNull {
                    val match = it.getValue(Matches::class.java)
                    if(match?.sportsName == sportName && match.day == day) match else null
                }

                Log.d(
                    "FirebaseRepository",
                    "Fetched ${matchList.size} matches for sport: $sportName on Day $day"
                )
                onResult(matchList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(
                    "FirebaseRepository",
                    "Error fetching matches by sport and day: ${error.message}"
                )
            }
        })
    }


    //fetch leaderboard info
    fun fetchLeaderboard(onResult: (List<LeaderboardEntry>) -> Unit) {
        db.child("leaderboard").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val leaderboard =
                    snapshot.children.mapNotNull { it.getValue(LeaderboardEntry::class.java) }
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
            collegeReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val currentEntry = snapshot.getValue(LeaderboardEntry::class.java)
                        currentEntry?.let {
                            val gold = it.goldCount + entry.goldCount
                            val silver = it.silverCount + entry.silverCount
                            val bronze = it.bronzeCount + entry.bronzeCount
                            val points = gold*3 + silver*2 + bronze*1
                            val updatedEntry = mapOf(
                                "goldCount" to gold,
                                "silverCount" to silver,
                                "bronzeCount" to bronze,
                                "points" to points
                            )
                            collegeReference.updateChildren(updatedEntry)
                        }
                    } else {
                        val points = entry.goldCount*3 + entry.silverCount*2 + entry.bronzeCount*1
                        val updates = mapOf(
                            "goldCount" to entry.goldCount,
                            "silverCount" to entry.silverCount,
                            "bronzeCount" to entry.bronzeCount,
                            "points" to points
                        )
                        db.child("leaderboard").child(entry.collegeName).updateChildren(updates)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("FirebaseRepository", "Error: ${error.message}")
                }
            })
        }
    fun deleteMatch(matchId: String, onResult: (Boolean) -> Unit) {
        db.child("matches").child(matchId).removeValue()
            .addOnSuccessListener {
                Log.d("FirebaseRepository", "Match deleted successfully.")
                onResult(true)
            }
            .addOnFailureListener { error ->
                Log.e("FirebaseRepository", "Error deleting match: ${error.message}")
                onResult(false)
            }
    }



}
