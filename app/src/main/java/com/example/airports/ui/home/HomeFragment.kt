package com.example.airports.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.airports.R
import com.example.airports.databinding.FragmentHomeBinding
import com.example.airports.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override fun getViewModelClass() = HomeViewModel::class.java

    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

    override fun setupViews() = with(binding) {
        button.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_detailsFragment)
        }
    }
}