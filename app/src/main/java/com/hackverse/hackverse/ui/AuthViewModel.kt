package com.hackverse.hackverse.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController

import com.github.dhaval2404.imagepicker.ImagePicker
import com.hackverse.hackverse.R
import com.hackverse.hackverse.data.repository.PaymentRepository
import com.hackverse.hackverse.data.repository.PublicKeyRepository
import com.hackverse.hackverse.utils.ApiException
import com.hackverse.hackverse.utils.Coroutines
import com.hackverse.hackverse.utils.NoInternetExcepetion
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class AuthViewModel(
    val repository: PublicKeyRepository,
    private val fragment: Fragment

) : ViewModel(
) {
    var image: ObservableField<Uri>? = ObservableField<Uri>()

    var onCameraClickListener: SignInSetupClickListener? = null
    var authListener: AuthListener? = null

    fun updatePic(view: View) {
        onCameraClickListener?.onCameraButtonClick(view)
    }

    fun onclickNext(view: View) {
        authListener?.onStarted()
        Coroutines.main {
            val unit = try {
                Log.v("image", image?.get().toString())
                val file = File(image?.get()?.path)
                val filePart = MultipartBody.Part.createFormData(
                    "image",
                    file.name,
                    RequestBody.create(MediaType.parse("image/*"), file)
                )

                val imageResponse =
                    repository.publicKey(
                        filePart
                    )//update this

                Log.v("after", "imageupload")


                imageResponse.let {
                    authListener?.onSuccess(it)
                    return@main
                }
            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetExcepetion) {
                authListener?.onFailure(e.message!!)

            }
//
        }


    }

    fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        if (resultCode == Activity.RESULT_OK) {
            val fileUri = data?.data
            image?.set(fileUri)
            image?.notifyChange()
            Log.v("Uri", image.toString())

            //You can get File object from intent
//            val file:File = ImagePicker.getFile(data)
//
//            //You can also get File Path from intent
//            val filePath:String = ImagePicker.getFilePath(data)

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Log.v("Uri", "error")
//            authListener?.onFailure(ImagePicker.getError(data))

        }

    }


//    fun onSignin(view: View) {
//    }

}