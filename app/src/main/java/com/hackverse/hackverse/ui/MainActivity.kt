package com.hackverse.hackverse.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.github.dhaval2404.imagepicker.ImagePicker
import com.hackverse.hackverse.R
import com.hackverse.hackverse.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(),ImageSetupClickListener {
    private var viewModel : ImageSetupViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get<ImageSetupViewModel>(ImageSetupViewModel::class.java)
        binding.imageSetupViewModel=viewModel
        viewModel!!.onCameraClickListener = this

    }


    override fun onCameraButtonClick(view: View) {
            Log.v("inside","on camera button click")

        ImagePicker.with(this)
            .crop()	    			//Crop image(Optional), Check Customization for more option
            .compress(1024)			//Final image size will be less than 1 MB(Optional)
            .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
            .start()
    }
}
