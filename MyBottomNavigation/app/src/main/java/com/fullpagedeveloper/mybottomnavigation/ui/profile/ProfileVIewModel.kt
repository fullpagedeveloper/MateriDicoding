package com.fullpagedeveloper.mybottomnavigation.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileVIewModel: ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Profile"
    }
    val text: LiveData<String> = _text
}