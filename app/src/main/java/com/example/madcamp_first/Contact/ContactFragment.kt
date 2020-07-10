package com.example.madcamp_first.Contact

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
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
import com.example.madcamp_first.R
import kotlinx.android.synthetic.main.activity_contact.view.*

//import android.R

private const val MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1

class ContactFragment : Fragment() {

    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.activity_contact, container, false)

        val adapter =
            ContactAdapter({ contact ->
                val intent = Intent(context, AddActivity::class.java)
                intent.putExtra(AddActivity.EXTRA_CONTACT_NAME, contact.name)
                intent.putExtra(AddActivity.EXTRA_CONTACT_NUMBER, contact.number)
                intent.putExtra(AddActivity.EXTRA_CONTACT_ID, contact.id)
                startActivity(intent)
            }, { contact ->
                deleteDialog(contact)
            })

        val lm = LinearLayoutManager(context)
        root.main_recycleview.adapter = adapter
        root.main_recycleview.layoutManager = lm
        root.main_recycleview.setHasFixedSize(true)

        if (context?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.READ_CONTACTS) }
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(Manifest.permission.READ_CONTACTS),
                MY_PERMISSIONS_REQUEST_READ_CONTACTS)
        }

        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
        context?.let {
            contactViewModel.getAll(it).observe(this, Observer<List<Contact>> { contacts ->
                adapter.setContacts(contacts!!)
            })
        }

        val addButton = root.findViewById<Button>(R.id.main_button)

        addButton.setOnClickListener {
            val intent = Intent(context, AddActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    private fun deleteDialog(contact: Contact) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Delete selected contact?")
            .setNegativeButton("NO") { _, _ -> }
            .setPositiveButton("YES") { _, _ ->
                contactViewModel.delete(contact)
            }
        builder.show()
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