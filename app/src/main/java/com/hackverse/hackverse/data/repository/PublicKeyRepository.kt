package com.hackverse.hackverse.data.repository

import com.hackverse.hackverse.data.network.MyApi
import com.hackverse.hackverse.data.network.SafeApiRequest
import com.hackverse.hackverse.data.network.responses.PublicKeySetup
import okhttp3.MultipartBody

class PublicKeyRepository(
    private val api: MyApi
) : SafeApiRequest() {

    suspend fun publicKey(
        image: MultipartBody.Part
    ): PublicKeySetup {
        return apiRequest { api.publicKey(image) }
    }

}