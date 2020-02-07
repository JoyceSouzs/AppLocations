package com.jmfs.locations.util

import android.view.View
import androidx.navigation.findNavController
import com.jmfs.locations.R

class MyHandlers {
    fun goToNavFragment(view: View){
        view.findNavController().navigate(R.id.toNavFragment)
    }

}