package com.android.mc.project.meowfacts.overview

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.android.mc.project.meowfacts.network.MeowFactsApi
import com.android.mc.project.meowfacts.network.MeowFactsList
import kotlinx.coroutines.launch


class OverviewViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val _facts = MutableLiveData<MeowFactsList>()
    val facts: LiveData<MeowFactsList>
        get() = _facts

    private val _factsNumber = String()
    var factsNumber: String = "5"
        get() = _factsNumber

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getMeowFacts("5")
    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    fun getMeowFacts(factsNumber: String) {
        viewModelScope.launch {
            try {
                _facts.value = MeowFactsApi.retrofitService.getFacts(factsNumber)
                _response.value = "Success: Facts retrieved"

            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }
}
