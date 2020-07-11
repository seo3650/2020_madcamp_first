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
import com.google.android.gms.maps.CameraUpdateFactory.newLatLng

class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mapFragment: MapFragment

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

        return root
    }



    companion object {
        @JvmStatic
        fun newInstance(): MapFragment {
            return MapFragment()
        }
    }
}