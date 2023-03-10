package com.example.fuelfinder

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.fuelfinder.databinding.ActivityMapsBinding
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.Marker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_maps.*
import java.io.IOException

class MapsActivity2 : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest
    private var locationUpdateState = false

    companion object{
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        private const val PLACE_PICKER_REQUEST = 3
        private const val REQUEST_CHECK_SETTINGS = 2
    }
        private fun setUpMap() {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) !=
                PackageManager.PERMISSION_GRANTED
            ) {

                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE
                )
                return
            }
            mMap.isMyLocationEnabled = true

            fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
                if (location != null) {
                    lastLocation = location
                    val currentLatLng)

                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12))
                }
            }
        }

    private fun placeMarkerOnMap(location: LatLng) {
            val markerOptions = MarkerOptions().position(location)
            val titleStr = getAddress(location)
            markerOptions.title(titleStr)
            mMap.addMarker(markerOptions)
        }
    private fun startLocationUpdates() {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) !=
                PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE
                )
                return
            }
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null /* Looper*/)
        }
    private fun createLocationRequest(){
            locationRequest = LocationRequest()
            locationRequest.interval = 10000
            locationRequest.fastestInterval = 5000
            locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        createLocationRequest()

            val builder = LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)
            val client = LocationServices.getSettingsClient(this)
            val task = client.checkLocationSettings(builder.build())

            task.addOnSuccessListener {
                locationUpdateState = true
                startLocationUpdates()
            }
            task.addOnFailureListener { e ->
                if (e is ResolvableApiException) {
                    try {
                        e.startResolutionForResult(this@MapsActivity2, REQUEST_CHECK_SETTINGS)
                    } catch (
                        sendEx:
                        IntentSender.SendIntentException
                    ) {
                    }
                }
            }
        }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data) {
            if (requestCode == REQUEST_CHECK_SETTINGS) {
                if (resultCode == Activity.RESULT_OK) {
                    locationUpdateState = true
                    startLocationUpdates()
                }
            }
        }
    }
        override fun onPause() {
        super.onPause()

        fusedLocationClient.removeLocationUpdates(locationCallback)
    }
        public override fun onResume(){
        super.onResume()
        if (!locationUpdateState) {
            startLocationUpdates()
        }
    }

        private fun loadPlacePicker() {
            val builder = PlacePicker.IntentBuilder()

            try {
                startActivityForResult(builder.build(this@MapsActivity2),
                PLACE_PICKER_REQUEST)
            } catch (e: GooglePlayServicesRepairableException) {
                e.printStackTrace()
            }
        }
        private fun getAddress(latLng: LatLng): String {
            val geocoder = Geocoder(this)
            val addresses: List<Address>?
            val address: Address?
            var addressText = ""

            try {
             addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
             if (null != addresses && addresses.isEmpty()) {
                 address = addresses[0]
                 for (i in 0 until address.maxAddressLineIndex) {
                     addressText += if (i == 0)
                         address.getAddressLine((i) else "\n"
                     + address.getAddressLine(i) }
                     }
                 }
             catch (e: IOException) {
                Log.e("MapActivity", e.localizedMessage)
    }
                return addressText
}

    private fun onActivityResult() {
        if (requestCode == RESULT_OK) {
            val place = PlacePicker.getPlace(this, data)
            var addressText = place.name.toString()
            addressText += "\n" + place.address.toString()
            placeMarkerOnMap(place.latLng)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val fab = findViewById<FloatingActionButton>(R.id.map)
            fab.setOnClickListener {
            loadPlacePicker()
        }

        locationCallback = object
        LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                super.onLocationResult(p0)
                lastLocation = p0.lastLocation
                placeMarkerOnMap(LatLng(lastLocation.latitude, lastLocation.longitude))
            }
        }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val abuja = LatLng(9.07, 7.49)
        mMap.addMarker(MarkerOptions().position(abuja).title("Marker in Abuja"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(abuja))
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.setOnMarkerClickListener(this)

        setUpMap()

        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            if (location != null) {
                lastLocation = location

                val currentLatLng = LatLng(location.latitude, location.longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
            }
        }

    override fun onMarkerClick(p0: Marker): Boolean {
        TODO("Not yet implemented")
    }
}
