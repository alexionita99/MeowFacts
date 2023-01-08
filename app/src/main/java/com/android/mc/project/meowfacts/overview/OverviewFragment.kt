package com.android.mc.project.meowfacts.overview

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.mc.project.meowfacts.R
import com.android.mc.project.meowfacts.database.MeowFactsDatabase
import com.android.mc.project.meowfacts.databinding.FragmentOverviewBinding

// Overview Fragment
class OverviewFragment : Fragment() {
    override fun onCreateView(
        // Inflate the view
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentOverviewBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_overview, container, false
        )

        // Get the context
        val application = requireNotNull(this.activity).application

        // Get an instance of the Database and get the DAO
        val dataSource = MeowFactsDatabase.getInstance(application).meowFactsDatabaseDao

        // Create an instance of the ViewModel Factory.
        val viewModelFactory = OverviewViewModelFactory(dataSource, application)

        // Get a reference to the ViewModel associated with this fragment.
        val viewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(OverviewViewModel::class.java)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        val adapter = FactsAdapter()
        binding.factsList.adapter = adapter

        // Observer for the facts
        viewModel.facts.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        // Observer for the response
        viewModel.response.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it == "Success") {
                    context?.toast("Success! Facts retrieved!")
                } else {
                    context?.toast("Failure! Try checking your internet connection")
                }

            }
        })

        // Button listener for "Get Facts!"
        binding.getButton.setOnClickListener {

            if (binding.factsNumber.text.length > 0) {
                if (binding.factsNumber.text.toString().toInt() > 0) {
                    viewModel.getMeowFacts(binding.factsNumber.text.toString())
                } else context?.toast("Enter a positive number!")
            } else context?.toast("Enter a positive number!")
        }

        return binding.root
    }
}

// Function to display toast messages
fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}