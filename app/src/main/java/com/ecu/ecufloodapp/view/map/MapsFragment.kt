package com.ecu.ecufloodapp.view.map

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ecu.ecufloodapp.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.data.kml.KmlLayer
import kotlinx.android.synthetic.main.fragment_maps.*


/**
 * A simple [Fragment] subclass.
 */
class MapsFragment : Fragment() {

    private lateinit var mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        mapView.onCreate(savedInstanceState)

        mapView.onResume() // needed to get the map to display immediately


        try {
            MapsInitializer.initialize(activity?.applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //fullScreenAll()
        mapView.getMapAsync { googleMap ->
            mMap = googleMap

            // Add a marker in Sydney and move the camera
            val initial_location = LatLng(34.65, -77.09)
            mMap.moveCamera(CameraUpdateFactory.newLatLng(initial_location))
            mMap.animateCamera(CameraUpdateFactory.zoomTo(12f), 2000, null);
            val layer = KmlLayer(
                mMap,
                R.raw.kmlfile, context
            )
            layer.addLayerToMap()
            printKmlId(layer)
        }
    }


    private fun fullScreenAll() {
        if (Build.VERSION.SDK_INT in 12..18) { // lower api
            val v = activity?.window?.decorView
            v?.systemUiVisibility = View.GONE
        } else {
            //for new api versions.
            val decorView = activity?.window?.decorView
            val uiOptions =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            decorView?.systemUiVisibility = uiOptions
        }
    }

    private fun printKmlId(layer: KmlLayer) {
        layer.setOnFeatureClickListener {
            val directions =
                MapsFragmentDirections.actionMapsFragmentToLocationDetail(it.id)
            findNavController().navigate(directions)
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

}



