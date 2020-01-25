package com.hackverse.hackverse.data.network

import android.util.Log
import com.hackverse.hackverse.data.network.responses.ImageSetup
import com.hackverse.hackverse.utils.BASE_URL
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface MyApi {


//    @FormUrlEncoded
//    @POST("user_profiles/new_profile.json")
//    suspend fun imageUpload(
//        @Field("image") image: String?
//    ): Response<>

    @Multipart
    @POST("identify")
    suspend fun imageUpload(
        @Part("image") image: MultipartBody.Part?
    ): Response<ImageSetup>


    companion object {
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): MyApi {


            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()
            Log.v("as","as")
            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}