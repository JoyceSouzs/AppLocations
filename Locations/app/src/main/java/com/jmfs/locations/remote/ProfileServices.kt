package com.jmfs.locations.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.facebook.ProfileTracker
import com.jmfs.locations.entity.UserProfile

class ProfileServices{
    val liveDataResult = MutableLiveData<UserProfile>()

    fun getProfile(){
        object : ProfileTracker() {
            override fun onCurrentProfileChanged(
                oldProfile: com.facebook.Profile?,
                currentProfile: com.facebook.Profile?
            ) {
                liveDataResult.value = UserProfile(
                    currentProfile?.id, currentProfile?.name)
                Log.d("profCurrent","${liveDataResult.value}")
            }
        }
    }
}