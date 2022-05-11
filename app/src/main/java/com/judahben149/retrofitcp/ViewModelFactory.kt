package com.judahben149.retrofitcp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.judahben149.retrofitcp.repository.MainRepository
import com.judahben149.retrofitcp.ui.viewmodel.MainViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory (private val repository: MainRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}