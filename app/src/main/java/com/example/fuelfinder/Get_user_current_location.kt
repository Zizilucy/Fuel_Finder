package com.example.fuelfinder

import android.Manifest
import android.content.ContentProviderClient
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.LocationRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class Get_user_current_location : AppCompatActivity() {
    lateinit var binding:Get_user_current_location
    lateinit var locationRequest: LocationRequest
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Get_user_current_location_Binding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        bindng.getlocation.setOnClickListener(){
            checkLocationPermission()
        }
    }
    private fun checkLocationPermission(){
        if(ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            )== PackageManager.PERMISSION_GRANTED
        ){
            checkGPS()

            }
        else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)100)
        }
    }
    private fun checkGPS(){
        locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 5000
        locationRequest.fastestInterval = 2000

        var builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
        builder.setAlwaysShow(true)
        val result = LocationServices.getSettingsClient(
            this.applicationContext
        )
            .checkLocationSettings(builder.build())
        result.addOnCompleteListener { task ->
            try {
                val response = task.getResult(
                    ApiException::class.java
                )
                getUserLocation()
            }catch (e: ApiException){
                e.printStackTrace()
                when(e.statusCode){
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {
                        val resolveApiException = e as ResolvableApiException
                        resolveApiException.startResolutionForResult(this,200)
                    }catch (sendIntentException : IntentSender.SendIntentException){
                    }
                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {

                    }
                }

            }
        }
    }
    private fun getUserLocation(){
        if(ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )!=PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            ) {
            return
        }
       fusedLocationProviderClient.lostLocation.addonCompleteListener { task ->
           val location = task.getResult()
           if(location!= null){
               try {
                   val geocoder = Geocoder(this, Locale.getDefault())
                   val address = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                   val address_line = address[0].getAddressLine(0)
                   binding.locationText(address_line)
                   val address_location = address[0].getAddressLine(0)

                   openLocation(address_location.toString())

               }catch (e: IDException){

               }
           }

       }
    }
    private fun openLocation(location:String){
        binding.locationText.setOnClickListener(){
            if(binding.locationText.text.isEmpty()){
                val uri.parse(uriString, "geo:0, 0?q, $location")
                val intent = Intent(Intent.ACTION_VIEW.uri)
                intent.setPackage("com.google.android.apps.maps")
                startActivity(intent)
            }
        }

    }
}