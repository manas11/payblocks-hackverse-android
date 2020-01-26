package com.hackverse.hackverse.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.hackverse.hackverse.R
import com.hackverse.hackverse.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityHomeBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_home)
        val decor = window.decorView
        decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        binding.medicine.setOnClickListener {   supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_frame_layout, MedicineFragment(), "dogDetails")
            .addToBackStack(null)
            .commit()
        }

        binding.payment.setOnClickListener {   supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_frame_layout, PaymentSetupFragment(), "dogDetails")
            .addToBackStack(null)
            .commit()
         }

        binding.settings.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_frame_layout, SettingsFragment(), "dogDetails")
                .addToBackStack(null)
                .commit()
        }
    }
}
