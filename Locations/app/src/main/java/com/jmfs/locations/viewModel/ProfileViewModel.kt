package com.jmfs.locations.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jmfs.locations.entity.UserProfile
import com.jmfs.locations.remote.ProfileServices

class ProfileViewModel(profileServices: ProfileServices) : ViewModel() {

    var profile: LiveData<UserProfile> = profileServices.liveDataResult

    init {
        profileServices.getProfile()
    }
}