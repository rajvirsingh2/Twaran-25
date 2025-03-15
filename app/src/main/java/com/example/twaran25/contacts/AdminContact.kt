package com.example.twaran25.contacts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.twaran25.R
import com.example.twaran25.data.models.Contact
import com.example.twaran25.data.viewmodel.ContactViewModel
import com.example.twaran25.databinding.ActivityAdminContactBinding
import com.example.twaran25.events.AdminEvents
import com.example.twaran25.games.AdminMatches
import com.example.twaran25.leaderboard.AdminLeaderboard

class AdminContact : AppCompatActivity() {
    private lateinit var binding: ActivityAdminContactBinding
    private val contactViewModel = ContactViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAdminContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        fetchContacts()
        navigation()

    }

    private fun fetchContacts() {
        contactViewModel.fetchContact() // Fetches all contacts

        contactViewModel.contacts.observe(this) { contacts ->  // Observe list of contacts
            if (!contacts.isNullOrEmpty()) {
                val adapter = AdminContactAdapter(this, contacts)
                binding.recyclerView.adapter = adapter
            } else {
                Log.e("AdminContact", "No contacts found in database.")
            }
        }
    }

    private fun navigation(){
        binding.btnContact.setOnClickListener {
            if (javaClass.simpleName != AdminContact::class.java.simpleName) {
                val intent = Intent(this, AdminContact::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        binding.btnEvents.setOnClickListener {
            if (javaClass.simpleName != AdminEvents::class.java.simpleName) {
                val intent = Intent(this, AdminEvents::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        binding.btnLeaderboard.setOnClickListener {
            if (javaClass.simpleName != AdminLeaderboard::class.java.simpleName) {
                val intent = Intent(this, AdminLeaderboard::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        binding.btnMatches.setOnClickListener {
            if (javaClass.simpleName != AdminMatches::class.java.simpleName) {
                val intent = Intent(this, AdminMatches::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }

}
