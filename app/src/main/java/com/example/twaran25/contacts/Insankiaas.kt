package com.example.twaran25.contacts

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.twaran25.R
import com.example.twaran25.data.viewmodel.ContactViewModel
import com.example.twaran25.databinding.ActivityInsankiaasBinding

class Insankiaas : AppCompatActivity() {
    private lateinit var binding: ActivityInsankiaasBinding
    private val contactViewModel: ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsankiaasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

        // Retrieve contact ID from intent and fetch contact details
        val contactId = intent.getStringExtra("query_id")
        if (!contactId.isNullOrEmpty()) {
            contactViewModel.fetchSingleContact(contactId)
        }

        // Observe contact LiveData to update UI
        contactViewModel.contact.observe(this, Observer { contact ->
            contact?.let {
                binding.studentName.setText(it.name)
                binding.emailId.setText(it.phoneno)
                binding.query.setText(it.queryAndCollege)
            }
        })

        binding.btnClear.setOnClickListener {
            val queryId = intent.getStringExtra("query_id")
            if (!queryId.isNullOrEmpty()) {
                deleteQuery(queryId)
            } else {
                Log.e("Insankiaas", "Query ID is null or empty.")
            }
        }



    }
    private fun deleteQuery(queryId: String) {
        contactViewModel.deleteContact(queryId)

        contactViewModel.deletionStatus.observe(this) { success ->
            if (success) {
                Toast.makeText(this, "Query deleted successfully!", Toast.LENGTH_SHORT).show()
                finish() // Close activity after deletion
            } else {
                Toast.makeText(this, "Failed to delete query.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
