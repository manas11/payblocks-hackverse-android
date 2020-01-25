package com.hackverse.hackverse.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.github.dhaval2404.imagepicker.ImagePicker
import com.hackverse.hackverse.R
import com.hackverse.hackverse.data.network.MyApi
import com.hackverse.hackverse.data.network.NetworkConnectionInterceptor
import com.hackverse.hackverse.data.network.responses.ImageSetup
import com.hackverse.hackverse.data.repository.ImageRepository
import com.hackverse.hackverse.databinding.ActivityMainBinding
import com.hackverse.hackverse.utils.hide
import com.hackverse.hackverse.utils.show
import com.hackverse.hackverse.utils.toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),ImageSetupListener,ImageSetupClickListener {
    private var viewModel : ImageSetupViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val decor = window.decorView
        decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR


        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = MyApi(networkConnectionInterceptor)
        val repository = ImageRepository(api)
        val factory = ImageSetupViewModelFactory(repository)



        viewModel = ViewModelProviders.of(this, factory).get<ImageSetupViewModel>(ImageSetupViewModel::class.java)
        binding.imageSetupViewModel=viewModel
        viewModel!!.imageSetupListener=this
        viewModel!!.onCameraClickListener = this

    }


    override fun onStarted() {
//        progress_bar.show()
    }

    override fun onFailure(message: String) {
//        progress_bar.hide()
        this.toast(message)
    }

    override fun onSuccessImageSetup(imageSetup: ImageSetup) {
//        progress_bar.hide()
        Log.v("success", "in activity")
        Intent(this, MainActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }

    override fun onCameraButtonClick(view: View) {
        ImagePicker.with(this)
            .crop()	    			//Crop image(Optional), Check Customization for more option
            .compress(1024)			//Final image size will be less than 1 MB(Optional)
            .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
            .start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel?.onActivityResult(requestCode, resultCode, data)

    }
}
