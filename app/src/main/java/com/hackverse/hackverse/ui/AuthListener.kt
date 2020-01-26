package com.hackverse.hackverse.ui

import com.hackverse.hackverse.data.network.responses.PublicKeySetup

interface AuthListener {

    fun onStarted()
    fun onSuccess(publicKeySetup: PublicKeySetup)
    fun onFailure(message: String)

}