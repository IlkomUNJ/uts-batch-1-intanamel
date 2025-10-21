package com.example.utsmocom

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ContactViewModel : ViewModel() {
    private val _contacts = mutableStateListOf<Contact>()
    val contacts: List<Contact> = _contacts

    fun addContact(contact: Contact) {
        _contacts.add(contact)
    }

    fun updateContact(index: Int, newContact: Contact) {
        if (index in _contacts.indices) {
            _contacts[index] = newContact
        }
    }
}
