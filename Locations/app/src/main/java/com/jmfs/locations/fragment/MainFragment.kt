package com.jmfs.locations.fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.jmfs.locations.R
import com.jmfs.locations.entity.UserProfile
import com.jmfs.locations.util.MyHandlers
import com.jmfs.locations.viewModel.PlaceViewModel
import com.jmfs.locations.viewModel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_main.view.*
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {


    private val viewModel by viewModel(ProfileViewModel::class)
    private var mCallbackManager: CallbackManager? = null
    private lateinit var binding: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflater.inflate(R.layout.fragment_main, container, false)
        binding.login_button.setPermissions("email")
        binding.login_button.fragment = this

        viewModel.profile.observe(this, Observer {
                profile ->  val id = profile.id
            val name = profile.name
            Log.d("profileFacebook","$name - $id")
            verifyAccess()
        })

        mCallbackManager = CallbackManager.Factory.create()
        binding.login_button.registerCallback(mCallbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    verifyAccess()
                }
                override fun onCancel() {}
                override fun onError(error: FacebookException?) {
                    Log.d("Erro", error?.message.toString())
                }
            })

        return binding
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mCallbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun verifyAccess(){
        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired
        if (isLoggedIn) {
            MyHandlers().goToNavFragment(binding)
        }
    }
}

