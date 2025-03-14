package com.example.twaran25.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.twaran25.data.models.Contact
import com.example.twaran25.data.repository.FirebaseRepository

class ContactViewModel: ViewModel() {
    private val repository = FirebaseRepository()
    private val _contact = MutableLiveData<List<Contact>>()
    val contact: LiveData<List<Contact>> get() = _contact

    private val _submissionStatus = MutableLiveData<Boolean>()
    val submissionStatus: LiveData<Boolean> get() = _submissionStatus

    fun submitContactRequest(contact: Contact){
        repository.contactRequest(contact)
    }

    fun fetchContact(){
        repository.fetchContact {
            _contact.value = it
        }
    }
}