package com.example.madcamp_first

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.madcamp_first.ui.main.PageViewModel
import com.example.madcamp_first.ui.main.PlaceholderFragment
import kotlinx.android.synthetic.main.activity_contact.*
import kotlinx.android.synthetic.main.activity_contact.view.*
import kotlinx.android.synthetic.main.activity_main.*

//import android.R

class ContactFragment : Fragment() {

    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java).apply {
//
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.activity_contact, container, false)

        val adapter = ContactAdapter({ contact ->
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

        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
        contactViewModel.getAll().observe(this, Observer<List<Contact>> { contacts ->
            adapter.setContacts(contacts!!)
        })

//        val button:  = root.findViewById(R.id.main_button)

        root.main_button.setOnClickListener {
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
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(): ContactFragment {
            return ContactFragment().apply {
                arguments = Bundle().apply {
                }
            }
        }
    }
}