package com.example.twaran25.data.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.twaran25.data.models.Contact
import com.example.twaran25.data.repository.FirebaseRepository

class FirebaseViewModel : ViewModel() {
    private val repository = FirebaseRepository()

    fun contactRequest(contact: Contact) {
        Log.d("FirebaseViewModel", "contactRequest called with: $contact")

        val uniqueId = repository.generateUniqueId()  // Generate unique ID from the repository
        uniqueId?.let {
            val contactWithId = contact.copy(id = it) // Add the unique ID to the contact
            repository.contactRequest(contactWithId)  // Save contact with unique ID
        }
    }
}
