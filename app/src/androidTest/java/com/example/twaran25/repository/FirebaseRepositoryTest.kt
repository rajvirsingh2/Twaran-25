package com.example.twaran25.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.twaran25.data.models.Contact
import com.example.twaran25.data.models.LeaderboardEntry
import com.example.twaran25.data.models.Matches
import com.example.twaran25.data.repository.FirebaseRepository
import com.google.firebase.database.FirebaseDatabase
import org.junit.Assert.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class FirebaseRepositoryTest {
    private lateinit var repository: FirebaseRepository
    private lateinit var db: FirebaseDatabase

    @Before
    fun setup() {
        db = FirebaseDatabase.getInstance()
        repository = FirebaseRepository()
    }

    @Test
    fun testSubmitContactRequest() = runBlocking {
        val contact = Contact(
            name = "Rajvir",
            email = "9041751873",
            sportsName = "Tennis"
        )

        repository.contactRequest(contact)

        val latch = CountDownLatch(1)
        val ref = db.reference.child("contacts")

        ref.get().addOnSuccessListener { snapshot ->
            val addedContact = snapshot.children.find {
                it.child("name").value == "Alice"
            }
            assertNotNull("Contact should be added to Firebase", addedContact)
            latch.countDown()
        }.addOnFailureListener {
            fail("Failed to fetch data from Firebase")
        }

        assertTrue("Timeout waiting for Firebase operation", latch.await(5, TimeUnit.SECONDS))
    }

    @Test
    fun testAddMatches() = runBlocking {
        val match = Matches(
            matchId = "Match123",
            sportsName = "Football",
            teamA = "IIIT Gwalior",
            teamB = "IIIT Allahabad",
            day = 1,
            time = "10:00 AM",
            date = "13 March 2025",
            venue = "Football Ground",
            teamAScore = 0,
            teamBScore = 0,
            winner = -1,
        )
        repository.addMatch(match)

        val latch = CountDownLatch(1)
        repository.fetchMatches {
            val addedMatches = it.find { it.matchId == "Match123" }
            assertNotNull("Match added to Firebase", addedMatches)
            latch.countDown()
        }
        assertTrue("Timeout waiting for Firebase operation", latch.await(5, TimeUnit.SECONDS))
    }

    @Test
    fun testUpdateLeaderboard() = runBlocking {
        val entry = LeaderboardEntry(
            collegeName = "testCollege",
            goldCount = 3,
            silverCount = 2,
            bronzeCount = 6,
            points = 27
        )

        repository.updateMedalCount(entry)
        assertNotNull(entry)
        assertEquals(3, entry.goldCount)
        assertEquals(2, entry.silverCount)
        assertEquals(6, entry.bronzeCount)
        assertEquals(27, entry.points)
    }
}
