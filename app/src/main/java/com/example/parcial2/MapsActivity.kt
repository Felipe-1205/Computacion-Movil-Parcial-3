package com.example.parcial2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.parcial2.databinding.ActivityMapsBinding
import com.google.android.gms.maps.UiSettings
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.Marker

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private val markerList = mutableListOf<Marker>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        binding.reset.setOnClickListener {
            mMap.clear()
        }
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val uiSettings: UiSettings = mMap.uiSettings
        uiSettings.isZoomControlsEnabled = true
        uiSettings.isRotateGesturesEnabled = true
        uiSettings.isTiltGesturesEnabled = true
        val initialPosition = LatLng(4.627308773036865, -74.06344864862382)
        val zoomLevel = 14.0f
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initialPosition, zoomLevel))
        mMap.setOnMapLongClickListener(GoogleMap.OnMapLongClickListener { latLng ->
            val markerOptions = MarkerOptions()
                .position(latLng)
                .title("PUNTO DE ENCUENTRO")
            val marker = mMap.addMarker(markerOptions)
            if (marker != null) {
                markerList.add(marker)
            }
            drawCircle(latLng, 1000.0, Color.GREEN, 3f)
            drawCircle(latLng, 800.0, Color.YELLOW, 3f)
            drawCircle(latLng, 400.0, Color.RED, 3f)
        })
    }
    private fun drawCircle(center: LatLng, radius: Double, color: Int, strokeWidth: Float) {
        val circleOptions = CircleOptions()
            .center(center)
            .radius(radius)
            .strokeColor(Color.BLACK)
            .strokeWidth(strokeWidth)
            .fillColor(color)
        mMap.addCircle(circleOptions)
    }
}