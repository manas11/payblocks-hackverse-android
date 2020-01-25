package com.hackverse.hackverse.ui

import com.hackverse.hackverse.data.network.responses.ImageSetup

interface ImageSetupListener {
    fun onStarted()
    fun onFailure(message: String)
    fun onSuccessImageSetup(imageSetup: ImageSetup)

}