package com.example.githubtubes.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubtubes.ui.main.detail.DetailUserViewModel

class ViewModelFactory (
    private val application: Application
) : ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DetailUserViewModel::class.java) ->
                DetailUserViewModel(application) as T
            else -> throw IllegalArgumentException("Unknow ViewModel")

        }
    }
}

