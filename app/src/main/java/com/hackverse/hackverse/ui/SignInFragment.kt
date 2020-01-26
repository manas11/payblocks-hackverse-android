package com.hackverse.hackverse.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.github.dhaval2404.imagepicker.ImagePicker
import com.hackverse.hackverse.R
import com.hackverse.hackverse.data.network.MyApi
import com.hackverse.hackverse.data.network.NetworkConnectionInterceptor
import com.hackverse.hackverse.data.network.responses.PublicKeySetup
import com.hackverse.hackverse.data.repository.PublicKeyRepository
import com.hackverse.hackverse.databinding.FragmentSigninBinding
import com.hackverse.hackverse.utils.hide
import com.hackverse.hackverse.utils.show
import com.hackverse.hackverse.utils.toast
import kotlinx.android.synthetic.main.fragment_signin.*

class SignInFragment: Fragment(), AuthListener, SignInSetupClickListener {
    private var viewModel: AuthViewModel? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val networkConnectionInterceptor = NetworkConnectionInterceptor(activity)
        val api = MyApi(networkConnectionInterceptor)
        val repository = PublicKeyRepository(api)
        val factory = AuthViewModelFactory(repository,this)

        val dataBinding = DataBindingUtil.inflate<FragmentSigninBinding>(
            inflater,
            R.layout.fragment_signin,
            container,
            false
        )


        viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        dataBinding.authViewModel = viewModel
        viewModel!!.authListener = this
        viewModel!!.onCameraClickListener = this

        return dataBinding.root

    }


    override fun onStarted() {
        progress_bar.show()
    }


    override fun onSuccess(publicKeySetup: PublicKeySetup) {
        progress_bar.hide()
        Log.v("logged in",publicKeySetup.publickey)
        this!!.findNavController().navigate(R.id.action_signinFragment_to_paymentSetupFragment)
/////////////////// put logic of sign in variable



//        context.toast("Welcome ${publicKeySetup.publickey}.")
//        Intent(activity, ::class.java).also {
//            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            startActivity(it)
//        }
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        context.toast(message)

    }
    override fun onCameraButtonClick(view: View) {

        ImagePicker.with(this)
            .cameraOnly()
            .crop(
                1f,
                1f
            )                    //Crop image(Optional), Check Customization for more option
            .compress(150)            //Final image size will be less than 1 MB(Optional)
            .maxResultSize(
                512,
                360
            )    //Final image resolution will be less than 1080 x 1080(Optional)
            .start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel?.onActivityResult(requestCode, resultCode, data)
    }

}