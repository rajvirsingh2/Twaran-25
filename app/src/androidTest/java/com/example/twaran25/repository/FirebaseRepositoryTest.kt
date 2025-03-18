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
