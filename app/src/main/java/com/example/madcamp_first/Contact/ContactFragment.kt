package com.example.madcamp_first.Contact

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.madcamp_first.MainActivity
import com.example.madcamp_first.R
import kotlinx.android.synthetic.main.activity_contact.view.*

//import android.R

private const val MULTIPLE_PERMISSION_REQUEST = 0
private const val REQUEST_EDIT = 3
private const val REQUEST_INSERT = 3

class ContactFragment : Fragment() {

    private lateinit var contactViewModel: ContactViewModel
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPermissions()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.activity_contact, container, false)

        adapter =
            ContactAdapter({ contact, _, _ ->
                edit(contact)
            }, { contact ->
                deleteDialog(contact)
            })

        val lm = LinearLayoutManager(context)
        root.main_recycleview.adapter = adapter
        root.main_recycleview.layoutManager = lm
        root.main_recycleview.setHasFixedSize(true)

        /* Get contacts */
        if (context?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.READ_CONTACTS) }
            == PackageManager.PERMISSION_GRANTED) {
            contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
            context?.let {
                contactViewModel.getAll(it).observe(this, Observer<List<Contact>> { contacts ->
                    adapter.setContacts(contacts!!)
                })
            }
        }


        val addButton = root.findViewById<Button>(R.id.main_button)

        addButton.setOnClickListener {
            insert()
        }

        return root
    }

    private fun checkPermissions() {
        /* Set permission */
        var rejectedPermission = ArrayList<String>()
        val requiredPermission: ArrayList<String> = arrayListOf(
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        for (permission in requiredPermission) {
            if (context?.let { ContextCompat.checkSelfPermission(it,permission) }
                != PackageManager.PERMISSION_GRANTED) {
                rejectedPermission.add(permission)
            }
        }

        if(rejectedPermission.isNotEmpty()) {
            val array = arrayOfNulls<String>(rejectedPermission.size)
            requestPermissions(
                rejectedPermission.toArray(array),
                MULTIPLE_PERMISSION_REQUEST
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            MULTIPLE_PERMISSION_REQUEST -> {
                if (grantResults.isEmpty()) {
                    return
                }
                for ((i, permission) in permissions.withIndex()) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        checkPermissions()
                    }
                    if (i == 0) {
                        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
                        context?.let {
                            contactViewModel.getAll(it).observe(this, Observer<List<Contact>> { contacts ->
                                adapter.setContacts(contacts!!)
                            })
                        }
                    }
                }
            }
        }
    }

    private fun deleteDialog(contact: Contact) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Delete selected contact?")
            .setNegativeButton("NO") { _, _ -> }
            .setPositiveButton("YES") { _, _ ->
                context?.let { contactViewModel.delete(it, contact) }
            }
        builder.show()
    }

    private fun insert() {
        val intent = Intent(ContactsContract.Intents.Insert.ACTION).apply {
            type = ContactsContract.RawContacts.CONTENT_TYPE
        }
        startActivityForResult(intent, REQUEST_INSERT)
    }

    private fun edit(contact: Contact) {
        val id: String = contact.id.toString()
        val section = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?"
        val sectionArgs = arrayOf(id)
        val c: Cursor = context?.contentResolver?.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null, section, sectionArgs,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " asc")
            ?: return

        if (c.moveToFirst()) {
            try {
                do {
                    val lookupKey = c.getString(c.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY))
                    val uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, lookupKey)

                    val editIntent: Intent = Intent(Intent.ACTION_EDIT).apply {
                        setDataAndType(uri, ContactsContract.Contacts.CONTENT_ITEM_TYPE)
                    }
                    editIntent.putExtra("finishActivityOnSaveCompleted", true)
                    editIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                    startActivityForResult(editIntent, REQUEST_EDIT)

                } while (c.moveToNext())
            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_EDIT) {
            contactViewModel.getAll(context!!)
        } else if (requestCode == REQUEST_INSERT) {
            contactViewModel.getAll(context!!)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): ContactFragment {
            return ContactFragment().apply {
                arguments = Bundle().apply {
                }
            }
        }
    }
}