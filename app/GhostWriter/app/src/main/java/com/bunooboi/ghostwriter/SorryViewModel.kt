package com.bunooboi.ghostwriter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SorryViewModel : ViewModel() {
    private val _who = MutableLiveData<Int>()
    val who: LiveData<Int> = _who

    private val _level = MutableLiveData<Int>()
    val level: LiveData<Int> = _level

    private val _why = MutableLiveData<Int>()
    val why: LiveData<Int> = _why

    init {
        _who.value = 0
    }

    fun setWho(who: Int) {
        _who.value = who
        System.out.println(who)
    }

    fun setLevel(level: Int) {
        _level.value = level
        System.out.println("$level level")
    }

    fun setWhy(why: Int) {
        _why.value = why
        System.out.println("$why why")
    }
}