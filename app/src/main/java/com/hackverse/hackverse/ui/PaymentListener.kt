package com.hackverse.hackverse.ui

import com.hackverse.hackverse.data.network.responses.PaymentSetup

interface PaymentListener {

    fun onStarted()
    fun onFailure(message: String)
    fun onSuccessPaymentSetup(paymentSetup: PaymentSetup)
}