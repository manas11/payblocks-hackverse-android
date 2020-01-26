package com.hackverse.hackverse.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hackverse.hackverse.data.repository.MedicineRepository
import com.hackverse.hackverse.data.repository.PaymentRepository
import com.hackverse.hackverse.data.repository.PublicKeyRepository


@Suppress("UNCHECKED_CAST")
class MedicineViewModelFactory(
    private val repository: MedicineRepository,
    private val activity: Fragment

) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MedicineViewModel(repository, activity) as T
    }

}