package com.example.madcamp_first.Contact

import android.app.Application
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import android.provider.Telephony.Mms.Addr.CONTACT_ID
import android.widget.Button
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.*

class ContactViewModel(application: Application): AndroidViewModel(application) {
    val contacts: MutableLiveData<List<Contact>> by lazy {
        MutableLiveData<List<Contact>>()
    }
    fun getAll(context: Context): LiveData<List<Contact>> {
        var dataList = mutableListOf<Contact>()

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
                    val contact = Contact(id.toLong(), name, number, initial) // todo: consider id
                    dataList.add(contact)
                }
                phoneCursor.close()

            }

        }
        c.close()
        contacts.value = dataList
        return contacts
    }

    fun delete(context: Context, contact: Contact) {
        val id: String = contact.id.toString()
        val section = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?"
        val sectionArgs = arrayOf(id)
        val c: Cursor = context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null, section, sectionArgs,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " asc")
            ?: return

        if (c.moveToFirst()) {
            try {
                do {
                    val lookupKey = c.getString(c.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY))
                    val uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, lookupKey)
                    context.contentResolver.delete(uri, null, null)
                } while (c.moveToNext())
            } catch (e: Exception) {
                e.stackTrace
            }
        }

        contacts.value = (contacts.value as MutableList<Contact>).minus(contact)//.remove(contact)
    }
}
