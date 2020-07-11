package com.example.madcamp_first.Map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.madcamp_first.R


private const val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1

class MapFragment : Fragment() {

    private lateinit var mapFragment: MapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.activity_map, container, false)

        return root
    }



    companion object {
        @JvmStatic
        fun newInstance(): MapFragment {
            return MapFragment()
        }
    }
}