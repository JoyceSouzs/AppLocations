package com.jmfs.locations.util

import android.app.Activity
import android.view.View
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.jmfs.locations.R

class MyHandlers {
    fun goToNavFragment(view: View){
        view.findNavController().navigate(R.id.toNavFragment)
    }

    fun goToMainFragment(activity: Activity){
       findNavController(activity,R.id.nav_host_fragment).navigateUp()
    }

}