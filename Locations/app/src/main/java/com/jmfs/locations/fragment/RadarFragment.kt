package com.jmfs.locations.fragment


import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.gms.location.*
import com.jmfs.locations.databinding.FragmentRadarBinding
import com.jmfs.locations.viewModel.ProfileViewModel
import org.koin.android.viewmodel.ext.android.getViewModel

/**
 * A simple [Fragment] subclass.
 */
class RadarFragment : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest
    private lateinit var binding: FragmentRadarBinding
    private var requestingLocationUpdates = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentRadarBinding
            .inflate(inflater, container, false)

        val viewModel: ProfileViewModel = getViewModel()
        viewModel.profile.observe(this, Observer {
                profile ->   binding.urlImage = profile.id.toString()
        })
        setupLocation()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (requestingLocationUpdates) {
            startLocationUpdates()
        }
    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    private fun setupLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)
        locationRequest = LocationRequest.create()
        locationRequest.interval = 10000
        locationRequest.fastestInterval = 5000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                Log.d("test", "onLocationResult")
                locationResult ?: return
                Log.d("test", "onLocationResult")
                for (location in locationResult.locations){
                    Log.d("test", "location: ${location.latitude}/${location.longitude}")
                    val locations = "${location.latitude}/${location.longitude}"
                    binding.textLocations.text = locations
                }
            }
        }
    }

    private fun startLocationUpdates() {
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback,
            Looper.getMainLooper())
    }

    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }
}
