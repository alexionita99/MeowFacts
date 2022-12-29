package com.android.mc.project.meowfacts.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getMeowFacts()
    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun getMeowFacts() {
        _response.value = "Set the Mars API Response here!"
    }
}