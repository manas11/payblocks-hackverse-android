package com.hackverse.hackverse.ui

import android.net.Uri
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableField

class ImageSetupViewModel(): ViewModel() {
    var image = ObservableField<Uri>()

    var onCameraClickListener: ImageSetupClickListener? = null

    fun updatePic(view: View) {
        onCameraClickListener?.onCameraButtonClick(view)
    }
}