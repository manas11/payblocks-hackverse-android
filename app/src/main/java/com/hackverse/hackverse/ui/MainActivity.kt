package com.hackverse.hackverse.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hackverse.hackverse.R
import com.hackverse.hackverse.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        val decor = window.decorView
        decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        binding.settings.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_frame_layout, SettingsFragment(), "dogDetails")
                .addToBackStack(null)
                .commit()
        }

    }
}
