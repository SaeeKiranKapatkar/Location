import android.Manifest

import android.annotation.SuppressLint

import android.content.pm.PackageManager

import android.location.Location

import android.os.Bundle

import android.view.View

import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity

import androidx.core.app.ActivityCompat

import androidx.core.content.ContextCompat
import com.example.myapplication.R

import com.google.android.gms.location.FusedLocationProviderClient

import com.google.android.gms.location.LocationServices



class MainActivity : AppCompatActivity() {



    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var locationTextView: TextView



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)



        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        locationTextView = findViewById(R.id.locationTextView)

    }



    fun getLocation(view: View) {

        if (ContextCompat.checkSelfPermission(

                this,

                Manifest.permission.ACCESS_FINE_LOCATION

            ) != PackageManager.PERMISSION_GRANTED

        ) {

            ActivityCompat.requestPermissions(

                this,

                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),

                REQUEST_LOCATION_PERMISSION

            )

        } else {

            getLastLocation()

        }

    }



    @SuppressLint("MissingPermission")

    private fun getLastLocation() {

        fusedLocationClient.lastLocation

            .addOnSuccessListener { location: Location? ->

                if (location != null) {

                    val latitude = location.latitude

                    val longitude = location.longitude

                    locationTextView.text = "Latitude: $latitude\nLongitude: $longitude"

                } else {

                    locationTextView.text = "Location unavailable"

                }

            }

    }



    companion object {

        private const val REQUEST_LOCATION_PERMISSION = 1

    }

}

