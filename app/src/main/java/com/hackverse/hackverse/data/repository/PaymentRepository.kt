package com.hackverse.hackverse.data.repository

import com.hackverse.hackverse.data.network.MyApi
import com.hackverse.hackverse.data.network.SafeApiRequest
import com.hackverse.hackverse.data.network.responses.PaymentSetup
import okhttp3.MultipartBody

class PaymentRepository(
    private val api: MyApi
) : SafeApiRequest() {

    suspend fun amountTransaction(
        image: MultipartBody.Part,
        amount: Int,
        private: String,
        public: String
    ): PaymentSetup {
        return apiRequest { api.amountTransaction(image, amount, private, public) }
    }

}