package com.jmfs.locations.fragment


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jmfs.locations.R
import com.jmfs.locations.adapter.PlacesAdapter
import com.jmfs.locations.viewModel.PlaceViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment() {

    companion object {
        private const val PERMISSION_REQUEST_CODE = 1

        private val REQUIRED_PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    private val viewModel by sharedViewModel(PlaceViewModel::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding =  inflater.inflate(
            R.layout.fragment_list
            , container, false)

        binding.recycler_places.layoutManager = LinearLayoutManager(context)

        viewModel.listPlaces.observe(this, Observer {
            binding.recycler_places.adapter = PlacesAdapter(it)
        })

        viewModel.loading.observe(this, Observer {
            binding.progress_bar.visibility = if (it) View.VISIBLE else View.GONE
            binding.recycler_places.visibility = if (it) View.GONE else View.VISIBLE
        })

        if (permissionsAlreadyGranted()) {
            viewModel.loadPlaces(requireContext())
        } else {
            requestPermissions(REQUIRED_PERMISSIONS, PERMISSION_REQUEST_CODE)
        }

        return binding
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (permissionsAlreadyGranted()) {
                viewModel.loadPlaces(requireContext())
            }
        }
    }

    private fun permissionsAlreadyGranted(): Boolean {
        for (permission in REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(context!!, permission) !=
                PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }
}
