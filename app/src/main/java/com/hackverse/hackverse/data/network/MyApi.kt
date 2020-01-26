package com.hackverse.hackverse.data.network

import android.util.Log
import com.hackverse.hackverse.data.network.responses.PaymentSetup
import com.hackverse.hackverse.data.network.responses.PublicKeySetup
import com.hackverse.hackverse.data.repository.PaymentRepository
import com.hackverse.hackverse.utils.BASE_URL
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface MyApi {

    @Multipart
    @POST("identify")
    suspend fun amountTransaction(
        @Part image: MultipartBody.Part?,
        @Part("amount") amount: Int?,
        @Part("private") private: String?,
        @Part("public") public: String?

    ): Response<PaymentSetup>



    @Multipart
    @POST("getpublickey")
    suspend fun publicKey(
        @Part image: MultipartBody.Part?
    ): Response<PublicKeySetup>



    companion object {
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): MyApi {
            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}