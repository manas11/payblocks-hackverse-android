package com.hackverse.hackverse.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.github.dhaval2404.imagepicker.ImagePicker
import com.hackverse.hackverse.data.repository.ImageRepository
import com.hackverse.hackverse.utils.ApiException
import com.hackverse.hackverse.utils.Coroutines
import com.hackverse.hackverse.utils.NoInternetExcepetion
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class ImageSetupViewModel(
    val repository: ImageRepository

    ): ViewModel(
) {
    var image: ObservableField<Uri>? = ObservableField<Uri>()

    var onCameraClickListener: ImageSetupClickListener? = null
    var imageSetupListener: ImageSetupListener? = null



    fun onclickNext(view: View) {

        imageSetupListener?.onStarted()

        Coroutines.main {
            val unit = try {

                Log.v("image",image?.get().toString())


//               File(Uri.parse("res:///" + R.drawable.ic_male_user_profile_picture).path )
//
//
//             val uri:Uri?   = Uri.parse(
//                   ContentResolver.SCHEME_ANDROID_RESOURCE +
//                       "://" + fragment.resources.getResourcePackageName(R.drawable.ic_male_user_profile_picture)
//                       + '/' + fragment.resources.getResourceTypeName(R.drawable.ic_male_user_profile_picture) + '/' + fragment.resources.getResourceEntryName(
//                   R.drawable.ic_male_user_profile_picture) )
//               val file = File(uri.toString())
//
//
//               val requestFile: RequestBody = RequestBody.create(
//                   MediaType.parse( ContentResolver.SCHEME_ANDROID_RESOURCE),
//                   file
//               )
//
//               val body: MultipartBody.Part =  MultipartBody.Part.createFormData("image", file.getName(), requestFile)
//                Log.v("yu","yu")

///////////////
                val file = File(image?.get().toString())
                val requestFile: RequestBody =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file)
                Log.v("after","request body create")

// MultipartBody.Part is used to send also the actual file name
                // MultipartBody.Part is used to send also the actual file name
                val body =
                    MultipartBody.Part.createFormData("image", file.getName(), requestFile)
                ////////////////////
                Log.v("po",body.toString())
                Log.v("after","multi body create")

                val imageResponse =
                    repository.imageUpload(body)//update this
                Log.v("after", "imageupload")


                imageResponse.let {
                    imageSetupListener?.onSuccessImageSetup(it)
                    return@main
                }
            } catch (e: ApiException) {
                imageSetupListener?.onFailure(e.message!!)
            } catch (e: NoInternetExcepetion) {
                imageSetupListener?.onFailure(e.message!!)

            }
//
        }

//        Coroutines.main {
//            val unit = try {
//                val authResponse = repository.userLogin("test1@test.com", "123456")
//                authResponse.let {
//                    authListener?.onSuccess(it)
//                    return@main
//                }
//
//            } catch (e: ApiException) {
//                authListener?.onFailure(e.message!!)
//            } catch (e: NoInternetExcepetion) {
//                authListener?.onFailure(e.message!!)
//
//            }
//
//        }

    }







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
            image?.set(fileUri)
            image?.notifyChange()
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