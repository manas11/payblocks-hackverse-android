package com.hackverse.hackverse.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hackverse.hackverse.R
import com.hackverse.hackverse.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var PRIVATE_MODE = 0
    private val PREF_NAME1 = "logged_out"
//    private val PREF_NAME2 = "privatekey"
//    private val PREF_NAME3 = "publickey"




    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val sharedPref: SharedPreferences = getSharedPreferences(PREF_NAME1, PRIVATE_MODE)
        if (sharedPref.getBoolean(PREF_NAME1, false)) {
            val homeIntent = Intent(this, HomeActivity::class.java)
            startActivity(homeIntent)
        } else {
            val editor = sharedPref.edit()
            editor.putBoolean(PREF_NAME1, true)
            editor.apply()


            val binding: ActivityMainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main)
            val decor = window.decorView
            decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//            settings.visibility = View.GONE
//            payment.visibility = View.GONE


        }
    }
}
