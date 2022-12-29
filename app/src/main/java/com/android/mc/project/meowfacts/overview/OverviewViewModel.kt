package com.android.mc.project.meowfacts.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.mc.project.meowfacts.network.MeowFactsApi
import com.android.mc.project.meowfacts.network.MeowFactsList
import kotlinx.coroutines.launch


class OverviewViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val _facts = MutableLiveData<MeowFactsList>()
    val facts: LiveData<MeowFactsList>
        get() = _facts

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
        viewModelScope.launch {
            try {
                _facts.value = MeowFactsApi.retrofitService.getFacts()
                _response.value = "Success: Facts retrieved"

            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }
}
