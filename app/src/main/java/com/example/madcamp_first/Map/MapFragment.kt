package com.example.madcamp_first.Map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.madcamp_first.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.OnMapReadyCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp_first.Contact.Contact
import com.example.madcamp_first.Contact.ContactAdapter
import com.example.madcamp_first.Contact.ContactViewModel
import com.google.android.gms.maps.CameraUpdateFactory.newLatLng
import kotlinx.android.synthetic.main.activity_contact.view.*

class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mapFragment: MapFragment
    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private lateinit var mMap: GoogleMap


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val marker = LatLng(35.241615, 128.695587)
        mMap.addMarker(MarkerOptions().position(marker).title("Marker LAB"))
        mMap.moveCamera(newLatLng(marker))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.activity_map, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapview) as SupportMapFragment

        mapFragment.getMapAsync(this)

        /* Get contacts */
        val adapter =
            ContactAdapter({ contact ->
            }, { contact ->
            })
        val lm = LinearLayoutManager(context)
        root.main_recycleview.adapter = adapter
        root.main_recycleview.layoutManager = lm
        root.main_recycleview.setHasFixedSize(true)
        val application = activity?.application?:return null
        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
//        contactViewModel = ViewModelProvider(
//            activity as FragmentActivity,
//            ContactViewModel.Factory(application))[ContactViewModel::class.java]
        context?.let {
            contactViewModel.getAll(it).observe(this, Observer<List<Contact>> { contacts ->
                adapter.setContacts(contacts!!)
            })
        }

        return root
    }

//    fun setContactView()

    companion object {
        @JvmStatic
        fun newInstance(): MapFragment {
            return MapFragment()
        }
    }
}