package com.example.madcamp_first.Contact

import android.app.Application
import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract
import android.widget.Button
import androidx.lifecycle.*

class ContactViewModel(application: Application): AndroidViewModel(application) {
    private val repository = ContactRepository(application)
    private val contacts = repository.getAll()

    fun getAll(context: Context): LiveData<List<Contact>> {
//        return this.contacts
        val contacts: MutableLiveData<List<Contact>> by lazy {
            MutableLiveData<List<Contact>>()
        }
        var dataList = mutableListOf<Contact>()//: mutableList<Contact>

        val c: Cursor = context.contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null, null, null,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " asc")
            ?: return contacts

        while (c.moveToNext()) {
            val id: String = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID))
            val name: String = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY))

            val phoneCursor = context.contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " +id,
                null, null
            )

            if (phoneCursor != null) {
                if (phoneCursor.moveToFirst()) {
                    val number: String = phoneCursor.getString(phoneCursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER))
                    val initial = name[0].toUpperCase()
                    val contact = Contact(0, name, number, initial) // todo: consider id
                    dataList.add(contact)
                }
                phoneCursor.close()

            }

        }
        c.close()
        contacts.value = dataList
        return contacts
    }

    fun insert(contact: Contact) {
        repository.insert(contact)
    }

    fun delete(contact: Contact) {
        repository.delete(contact)
    }
    val button: LiveData<Button>? = null
}
