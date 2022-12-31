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


class OverviewFragment : Fragment() {
    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the OverviewFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentOverviewBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_overview, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = MeowFactsDatabase.getInstance(application).meowFactsDatabaseDao

        // Create an instance of the ViewModel Factory.

        val viewModelFactory = OverviewViewModelFactory(dataSource,application)

        // Get a reference to the ViewModel associated with this fragment.
        val viewModel =
            ViewModelProvider(
                this, viewModelFactory).get(OverviewViewModel::class.java)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        val adapter = FactsAdapter()
        binding.factsList.adapter = adapter

        viewModel.facts.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        binding.getButton.setOnClickListener {

            if (binding.factsNumber.text.length > 0){
                if (binding.factsNumber.text.toString().toInt() > 0) {
                    viewModel.getMeowFacts(binding.factsNumber.text.toString())
                }
                else context?.toast("Enter a positive number!")
        }
            else context?.toast("Enter a positive number!")
        }

        return binding.root
    }
}


inline fun Context.toast(message:String){
    Toast.makeText(this, message , Toast.LENGTH_SHORT).show()
}