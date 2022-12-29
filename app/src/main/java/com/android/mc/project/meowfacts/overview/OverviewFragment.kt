package com.android.mc.project.meowfacts.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.android.mc.project.meowfacts.R
import com.android.mc.project.meowfacts.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentOverviewBinding>(inflater,
            R.layout.fragment_overview,container,false)
        return binding.root
    }
}


