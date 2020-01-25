package com.hackverse.hackverse.data.repository

import com.hackverse.hackverse.data.network.MyApi
import com.hackverse.hackverse.data.network.SafeApiRequest
import com.hackverse.hackverse.data.network.responses.ImageSetup
import okhttp3.MultipartBody

class ImageRepository(
    private val api: MyApi
) : SafeApiRequest() {

    suspend fun imageUpload(image: MultipartBody.Part): ImageSetup {
        return apiRequest { api.imageUpload(image) }
    }

}