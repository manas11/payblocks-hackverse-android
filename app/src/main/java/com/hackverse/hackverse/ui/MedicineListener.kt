package com.hackverse.hackverse.ui

import com.hackverse.hackverse.data.network.responses.MedicineSetup
import com.hackverse.hackverse.data.network.responses.PublicKeySetup

interface MedicineListener {


    fun onStarted()
    fun onSuccess(medicineSetup: MedicineSetup)
    fun onFailure(message: String)

}