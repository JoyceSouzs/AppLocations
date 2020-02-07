package com.jmfs.locations.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.jmfs.locations.databinding.FragmentProfileBinding
import com.jmfs.locations.entity.UserProfile
import com.jmfs.locations.viewModel.ProfileViewModel
import org.koin.android.viewmodel.ext.android.getViewModel

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentProfileBinding
            .inflate(inflater, container, false)

        val viewModel: ProfileViewModel = getViewModel()
        viewModel.profile.observe(this, Observer {
                profile ->
                   var userProfile = UserProfile(profile.id,profile.name)
                        binding.userProfile = userProfile
                        binding.urlImage = userProfile.id

            //binding.urlImage = profile.id.toString()
            //binding.textName.text = profile.name.toString()
        })

        return binding.root
    }
}
