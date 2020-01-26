package com.hackverse.hackverse.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hackverse.hackverse.data.repository.PaymentRepository

@Suppress("UNCHECKED_CAST")
class PaymentSetupViewModelFactory(
    private val repository: PaymentRepository,
    private val activity: Fragment

) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PaymentSetupViewModel(repository,activity) as T
    }

}