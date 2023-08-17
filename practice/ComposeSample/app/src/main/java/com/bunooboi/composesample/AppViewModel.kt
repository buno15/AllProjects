package com.bunooboi.composesample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val preferencesRepository: PreferencesRepository) : ViewModel() {
    fun saveData(key: String, value: String) {
        preferencesRepository.putString(key, value)
    }

    fun retrieveData(key: String): String? {
        return preferencesRepository.getString(key)
    }
}