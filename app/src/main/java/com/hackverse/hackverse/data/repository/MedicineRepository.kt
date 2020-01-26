package com.hackverse.hackverse.data.repository

import com.hackverse.hackverse.data.network.MyApi
import com.hackverse.hackverse.data.network.SafeApiRequest
import com.hackverse.hackverse.data.network.responses.MedicineSetup
import com.hackverse.hackverse.data.network.responses.PaymentSetup
import okhttp3.MultipartBody

class MedicineRepository(
    private val api: MyApi
) : SafeApiRequest() {

    suspend fun medicineSetup(
        image: MultipartBody.Part,
        amount: Int,
        private: String,
        public: String
    ): MedicineSetup {
        return apiRequest { api.medicineSetup(image, amount, private, public) }
    }
}