package com.example.madcamp_first.Map

import android.graphics.Color
import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.madcamp_first.R
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.OnMapReadyCallback
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.madcamp_first.Contact.Contact
import com.example.madcamp_first.Contact.ContactAdapter
import com.example.madcamp_first.Contact.ContactViewModel
import com.google.android.gms.maps.*
import com.google.android.gms.maps.CameraUpdateFactory.newLatLng
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.activity_contact.view.*
import java.lang.Double.parseDouble

class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var contactViewModel: ContactViewModel
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var selectedContact: Contact? = null

    private fun placeMarkerOnMap(location: LatLng) {
        val markerOptions = MarkerOptions().position(location)
//        mMap.addMarker(markerOptions
//            .position(location)
//            .icon(bitmapDescriptorFromVector(context as Activity, R.drawable.ic_user_location)))
    }
    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }


    private fun setUpMap() {
        /*permission*/
        if (ActivityCompat.checkSelfPermission(
                context as Activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                context as Activity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }

        /*get my location*/
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(context as Activity) { location ->
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                placeMarkerOnMap(currentLatLng)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 16f))
                }
            }
        return
    }

    private lateinit var mMap: GoogleMap
    private lateinit var lastLocation: Location

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMapLongClickListener  { onMapLongClick(it) }
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)
        setUpMap()
    }

    private fun onMapLongClick(point: LatLng) {
        selectedContact?:return
        val application = activity?.application?: return
        val repository = PinRepository(application)
        val name = selectedContact!!.name
        mMap.addMarker(MarkerOptions().position(point).title(name))
        mMap.animateCamera(newLatLng(point))
        val position = point.latitude.toString() + "," + point.longitude.toString()

        repository.insert(Pin(position, name))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.activity_map, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapview) as SupportMapFragment

        mapFragment.getMapAsync(this)

        fusedLocationClient =
            context?.let { LocationServices.getFusedLocationProviderClient(it) }!! // oncreate에 해야될 수도 있다

        /* Get contacts */
        val adapter =
            ContactAdapter({ contact, viewList, view ->
                for (view in viewList) {
                    view.setBackgroundColor(Color.WHITE)
                }
                context?.resources?.getColor(R.color.colorSelected)?.let { view.setBackgroundColor(it) }
                selectedContact = contact

            }, { _ ->
            })
        val lm = LinearLayoutManager(context)
        root.main_recycleview.adapter = adapter
        root.main_recycleview.layoutManager = lm
        root.main_recycleview.setHasFixedSize(true)

        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
        context?.let {
            contactViewModel.getAll(it).observe(this, Observer<List<Contact>> { contacts ->
                adapter.setContacts(contacts!!)
            })
        }

        val application = activity?.application?: return root
        val repository = PinRepository(application)
        val pins = repository.getAll()
        pins.observe(this, Observer<List<Pin>> {
            setInitPin(it)
        })

        return root
    }

    private fun setInitPin(pins: List<Pin>) {
        if (pins == null) {
            return
        }
        for (pin in pins!!) {
            val positionStr = pin.position.split(",")
            val latitude = parseDouble(positionStr[0])
            val longitude = parseDouble(positionStr[1])
            val position = LatLng(latitude, longitude)
            mMap.addMarker(pin.position?.let { MarkerOptions().position(position).title(pin.name) })
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        @JvmStatic
        fun newInstance(): MapFragment {
            return MapFragment()
        }
    }

    override fun onMarkerClick(p0: Marker?): Boolean = false
}