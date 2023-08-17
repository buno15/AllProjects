package com.bunooboi.composesample

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(private val preferencesRepository: PreferencesRepository) : ViewModel() {

    fun saveData(key: String, value: String) {
        preferencesRepository.putString(key, value)
    }

    fun retrieveData(key: String): String? {
        return preferencesRepository.getString(key)
    }
}
