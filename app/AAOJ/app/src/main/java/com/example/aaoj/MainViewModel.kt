package com.example.aaoj

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val mutablePid = MutableLiveData<String>("ITP1_1_A")
    val pid: LiveData<String> get() = mutablePid

    fun setPid(pid: String) {
        mutablePid.value = pid
    }
}