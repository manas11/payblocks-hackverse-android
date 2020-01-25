package com.hackverse.hackverse.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableField
import com.github.dhaval2404.imagepicker.ImagePicker

class ImageSetupViewModel(): ViewModel() {
    var image = ObservableField<Uri>()

    var onCameraClickListener: ImageSetupClickListener? = null

    fun updatePic(view: View) {
        onCameraClickListener?.onCameraButtonClick(view)
    }

    fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        if (resultCode == Activity.RESULT_OK) {
            val fileUri = data?.data
            image.set(fileUri)
            image.notifyChange()
            Log.v("Uri",image.toString())

            //You can get File object from intent
//            val file:File = ImagePicker.getFile(data)
//
//            //You can also get File Path from intent
//            val filePath:String = ImagePicker.getFilePath(data)

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Log.v("Uri","error")
//            authListener?.onFailure(ImagePicker.getError(data))

        }

    }
}