package com.android.mc.project.meowfacts.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.mc.project.meowfacts.network.MeowFact
import com.android.mc.project.meowfacts.network.MeowFactsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        MeowFactsApi.retrofitService.getFacts().enqueue(
            object: Callback<List<MeowFact>> {
                override fun onResponse(call: Call<List<MeowFact>>,
                                        response: Response<List<MeowFact>>) {
                    _response.value =
                        "Success: ${response.body()?.size} Mars properties retrieved"
                }

                override fun onFailure(call: Call<List<MeowFact>>, t: Throwable) {
                    _response.value = "Failure: " + t.message
                }
            })
    }
}