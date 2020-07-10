package com.example.madcamp_first.Contact

import android.app.Application
import androidx.lifecycle.*
import com.example.madcamp_first.Contact.Contact
import com.example.madcamp_first.Contact.ContactRepository

class ContactViewModel(application: Application): AndroidViewModel(application) {
    private val repository =
        ContactRepository(application)
    private val contacts = repository.getAll()

    fun getAll(): LiveData<List<Contact>> {
        return this.contacts
    }

    fun insert(contact: Contact) {
        repository.insert(contact)
    }

    fun delete(contact: Contact) {
        repository.delete(contact)
    }
}
