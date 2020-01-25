package com.hackverse.hackverse.ui

import android.app.Activity
import android.app.AppComponentFactory
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BaseObservable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hackverse.hackverse.data.repository.ImageRepository

@Suppress("UNCHECKED_CAST")
class ImageSetupViewModelFactory(
    private val repository: ImageRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ImageSetupViewModel(repository) as T
    }

}