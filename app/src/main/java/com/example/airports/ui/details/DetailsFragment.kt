package com.example.airports.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.airports.R
import com.example.airports.databinding.FragmentDetailsBinding
import com.example.airports.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment: BaseFragment<DetailsViewModel, FragmentDetailsBinding>() {

    override fun getViewModelClass() = DetailsViewModel::class.java

    override fun getViewBinding() = FragmentDetailsBinding.inflate(layoutInflater)
}