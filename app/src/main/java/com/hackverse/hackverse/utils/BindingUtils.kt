package com.hackverse.hackverse.utils

import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.hackverse.hackverse.R
import com.hackverse.hackverse.utils.GlideApp


@BindingAdapter("app:image_url")
fun loadImage(view: ImageView, url: Uri?) {
    Log.v("inside load image",url.toString())
    if(url != null) {
        Glide.with(view)
            .load(url)
            .into(view)
    }
    else{
        view.setImageResource(R.drawable.ic_male_user_profile_picture)
    }
}