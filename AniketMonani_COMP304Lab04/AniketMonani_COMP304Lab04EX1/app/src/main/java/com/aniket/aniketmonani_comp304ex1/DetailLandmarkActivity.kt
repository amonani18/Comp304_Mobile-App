package com.aniket.aniketmonani_comp304ex1

import android.location.Geocoder
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.aniket.aniketmonani_comp304ex1.database.LandmarkEntity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException


class DetailLandmarkActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var landmark: LandmarkEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_landmark)

        landmark = intent.getParcelableExtra("EXTRA_LANDMARK") ?: return


        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        val toggleButton = findViewById<Button>(R.id.button_toggle_map)
        toggleButton.setOnClickListener {
            map.mapType = if (map.mapType == GoogleMap.MAP_TYPE_NORMAL) {
                GoogleMap.MAP_TYPE_SATELLITE
            } else {
                GoogleMap.MAP_TYPE_NORMAL
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.mapType = GoogleMap.MAP_TYPE_NORMAL
        val address = landmark.address
        val geocoder = Geocoder(this)
        try {
            val addressList = geocoder.getFromLocationName(address, 1)
            if (addressList != null && addressList.isNotEmpty()) {
                val location = addressList[0]
                val landmarkLatLng = LatLng(location.latitude, location.longitude)
                map.addMarker(MarkerOptions().position(landmarkLatLng).title(landmark.name))
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(landmarkLatLng, 15f))
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}

