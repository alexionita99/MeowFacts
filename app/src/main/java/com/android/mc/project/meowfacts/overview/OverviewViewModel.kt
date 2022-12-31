package com.android.mc.project.meowfacts.overview

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.android.mc.project.meowfacts.database.MeowFact
import com.android.mc.project.meowfacts.database.MeowFactsDatabaseDao
import com.android.mc.project.meowfacts.network.MeowFactsApi
import com.android.mc.project.meowfacts.network.MeowFactsList
import kotlinx.coroutines.launch


class OverviewViewModel(
    val dataSource: MeowFactsDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val _facts = MutableLiveData<List<MeowFact>>()
    val facts: LiveData<List<MeowFact>>
        get() = _facts

    private val _factsToAdd = MutableLiveData<List<String>>()
    val factsToAdd: LiveData<List<String>>
        get() = _factsToAdd

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
                _factsToAdd.value = MeowFactsApi.retrofitService.getFacts(factsNumber).facts
                dataSource.clear()
                insert(factsToAdd.value!!)
                _facts.value = dataSource.getAllFacts()

                _response.value = "Success: Facts retrieved"


            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }

    private suspend fun insert(facts: List<String>) {
        val factList = ArrayList<MeowFact>()
        for (fact: String in facts)
            factList.add(MeowFact(fact = fact))
        dataSource.insertAll(factList)
        Log.d("AICI", factList as String)
    }

}
