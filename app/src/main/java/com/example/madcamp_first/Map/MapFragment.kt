package com.example.madcamp_first.Map

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Typeface
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.madcamp_first.Contact.Contact
import com.example.madcamp_first.Contact.ContactAdapter
import com.example.madcamp_first.Contact.ContactViewModel
import com.example.madcamp_first.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.CameraUpdateFactory.newLatLng
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.activity_contact.view.*
import kotlinx.android.synthetic.main.custom_map_marker.view.*
import java.lang.Double.parseDouble

class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var contactViewModel: ContactViewModel
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var selectedContact: Contact? = null
    private lateinit var markerView: View
    private lateinit var markerName: TextView

    private fun placeMarkerOnMap(location: LatLng) {
        val markerOptions = MarkerOptions().position(location)
        mMap.addMarker(markerOptions
            .position(location)
            .icon(bitmapDescriptorFromVector(context as Activity, R.drawable.ic_user_location))
            .title("You"))
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
        /* Set click event */
        mMap.setOnMapLongClickListener  { onMapLongClick(it) }
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)
        /* Set init position */
        setUpMap()
        /* Set custom marker */
        setCustomMarkerView()
    }

    /* Add marker at longClick */
    private fun onMapLongClick(point: LatLng) {
        selectedContact?:return
        val application = activity?.application?: return
        val repository = PinRepository(application)
        val name = selectedContact!!.name

        /* Set info window */
        /* Add marker */
        addMarker(name, point)
        mMap.animateCamera(newLatLng(point))
        val position = positionToString(point)


        repository.insert(Pin(position, name))
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        val center: CameraUpdate = newLatLng(marker.position)
        mMap.animateCamera(center)
        if (marker.title == "You") {
            return true
        }
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Delete selected marker?")
            .setNegativeButton("NO") { _, _ -> }
            .setPositiveButton("YES") { _, _ ->
                deleteMarker(marker)
            }
        builder.show()
        return true

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
        pins.observeOnce(this, Observer<List<Pin>> {
            if (it != null) {
                setInitPin(it)
            }
        })

        return root
    }

    // For observing once only
    private fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
        observe(lifecycleOwner, object: Observer<T> {
            override fun onChanged(t: T?) {
                observer.onChanged(t)
                removeObserver(this)
            }
        })
    }

    private fun setInitPin(pins: List<Pin>) {
        for (pin in pins) {
            val positionStr = pin.position.split(",")
            val latitude = parseDouble(positionStr[0])
            val longitude = parseDouble(positionStr[1])
            val position = LatLng(latitude, longitude)
            addMarker(pin.name, position)
        }
    }

    private fun deleteMarker(marker: Marker) {
        val application = activity?.application?: return
        val repository = PinRepository(application)
        marker.remove()
        val position = positionToString(marker.position)
        repository.delete(Pin(position, marker.title))
    }

    private fun positionToString(point: LatLng): String {
        return point.latitude.toString() + "," + point.longitude.toString()
    }

    private fun setCustomMarkerView() {
        markerView = (context as Activity).layoutInflater.inflate(R.layout.custom_map_marker, null)
        markerName = markerView.name
    }

    private fun createDrawableFromView(view: View): Bitmap {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)

        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels)
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels)
        view.buildDrawingCache()

        val bitmap = Bitmap.createBitmap(
            view.measuredWidth,
            view.measuredHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    private fun addMarker(name: String, position: LatLng) {
        val face: Typeface = Typeface.create("sans-serif-medium", Typeface.BOLD)
        markerName.text = name
        markerName.typeface = face
        mMap.addMarker(MarkerOptions()
            .position(position)
            .icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(markerView)))
            .title(name))
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        @JvmStatic
        fun newInstance(): MapFragment {
            return MapFragment()
        }
    }
}