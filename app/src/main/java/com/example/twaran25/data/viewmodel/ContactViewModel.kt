package com.example.twaran25.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.twaran25.data.models.Contact
import com.example.twaran25.data.repository.FirebaseRepository

class ContactViewModel : ViewModel() {
    private val repository = FirebaseRepository()


    private val _contacts = MutableLiveData<List<Contact>>()
    val contacts: LiveData<List<Contact>> get() = _contacts

    private val _contact = MutableLiveData<Contact?>()
    val contact: LiveData<Contact?> get() = _contact
    private val _deletionStatus = MutableLiveData<Boolean>()
    val deletionStatus: LiveData<Boolean> get() = _deletionStatus

    private val _submissionStatus = MutableLiveData<Boolean>()
    val submissionStatus: LiveData<Boolean> get() = _submissionStatus

    fun submitContactRequest(contact: Contact) {
        repository.contactRequest(contact)
    }
    fun deleteContact(contactId: String) {
        repository.deleteContact(contactId) { success ->
            _deletionStatus.postValue(success)
        }
    }
    fun fetchContact() {
        repository.fetchContact { fetchedContacts ->
            _contacts.value = fetchedContacts
        }
    }

    fun fetchSingleContact(contactId: String) {
        repository.fetchContact(contactId) { fetchedContact ->
            _contact.value = fetchedContact
        }
    }
}
