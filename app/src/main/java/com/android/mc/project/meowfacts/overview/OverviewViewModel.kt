package com.android.mc.project.meowfacts.overview

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.android.mc.project.meowfacts.database.MeowFact
import com.android.mc.project.meowfacts.database.MeowFactsDatabaseDao
import com.android.mc.project.meowfacts.network.MeowFactsApi
import com.android.mc.project.meowfacts.network.MeowFactsList
import kotlinx.coroutines.launch

//OverviewViewModel, that has the datasource and context
class OverviewViewModel(
    val dataSource: MeowFactsDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    // A response string to keep the users updated about the status of the retrieval
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    // Get the LiveData from Room
    val facts = dataSource.getAllFacts()

    // Used to retrieve the facts and insert them in the database
    private var factsToAdd : List<String> = listOf()

    // On initialization, get 5 facts
    init {
        getMeowFacts("5")
    }

    // Function to get the facts and update the values
    fun getMeowFacts(factsNumber: String) {
        viewModelScope.launch {
            try {
                factsToAdd = MeowFactsApi.retrofitService.getFacts(factsNumber).facts
                dataSource.clear()
                insert(factsToAdd!!)
                _response.value = "Success"

            } catch (e: Exception) {
                _response.value = "Failure"
            }
        }
    }

    // A suspend function to process the retrieved facts and insert them  in the database
    private suspend fun insert(facts: List<String>) {
        val factList = ArrayList<MeowFact>()
        for (fact: String in facts)
            factList.add(MeowFact(fact = fact))
        dataSource.insertAll(factList)
    }

}
