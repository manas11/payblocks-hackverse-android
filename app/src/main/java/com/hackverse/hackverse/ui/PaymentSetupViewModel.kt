package com.hackverse.hackverse.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.github.dhaval2404.imagepicker.ImagePicker
import com.hackverse.hackverse.data.repository.PaymentRepository
import com.hackverse.hackverse.utils.ApiException
import com.hackverse.hackverse.utils.Coroutines
import com.hackverse.hackverse.utils.NoInternetExcepetion
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class PaymentSetupViewModel(
    val repository: PaymentRepository,
    private val fragment: Fragment

) : ViewModel(
) {
    var image: ObservableField<Uri>? = ObservableField<Uri>()
    var amount: String? = null
    var onCameraClickListener: PaymentSetupClickListener? = null
    var paymentListener: PaymentListener? = null

    ///////////////
    val public: String = "0x6FF00e32a8821326B93CDaa451F73360e530CD47"
    val private: String = "ce05dc09636308e724d2455db51fd642f4c21d831ca12904aec8f1dcafdc7745"
    ///////////////


    fun updatePic(view: View) {
        onCameraClickListener?.onCameraButtonClick(view)
    }
    fun onclickNext(view: View) {
        paymentListener?.onStarted()
        Coroutines.main {
            val unit = try {
                Log.v("image", image?.get().toString())
                val file = File(image?.get()?.path)
                Log.v("after", amount)
                val filePart = MultipartBody.Part.createFormData(
                    "image",
                    file.name,
                    RequestBody.create(MediaType.parse("image/*"), file)
                )

                val imageResponse =
                    repository.amountTransaction(
                        filePart,
                        amount!!.toInt(),
                        private,
                        public
                    )//update this

                Log.v("after", "imageupload")


                imageResponse.let {
                    paymentListener?.onSuccessPaymentSetup(it)
                    return@main
                }
            } catch (e: ApiException) {
                paymentListener?.onFailure(e.message!!)
            } catch (e: NoInternetExcepetion) {
                paymentListener?.onFailure(e.message!!)

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
}