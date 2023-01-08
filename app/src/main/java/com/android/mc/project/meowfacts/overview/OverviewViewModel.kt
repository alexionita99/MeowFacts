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

    val facts = dataSource.getAllFacts();

    private val _factsToAdd = MutableLiveData<List<String>>()
    val factsToAdd: LiveData<List<String>>
        get() = _factsToAdd

    private val _factsNumber = String()
    var factsNumber: String = "5"
        get() = _factsNumber


    init {
        getMeowFacts("5")
    }


    fun getMeowFacts(factsNumber: String) {
        viewModelScope.launch {
            try {
                _factsToAdd.value = MeowFactsApi.retrofitService.getFacts(factsNumber).facts
                dataSource.clear()
                insert(factsToAdd.value!!)
                _response.value = "Success"

            } catch (e: Exception) {
                _response.value = "Failure"
            }
        }
    }

    private suspend fun insert(facts: List<String>) {
        val factList = ArrayList<MeowFact>()
        for (fact: String in facts)
            factList.add(MeowFact(fact = fact))
        dataSource.insertAll(factList)
    }

}
