package com.example.airports.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.airports.R
import com.example.airports.databinding.FragmentDetailsBinding
import com.example.airports.domain.model.Airport
import com.example.airports.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<DetailsViewModel, FragmentDetailsBinding>() {

    private val args: DetailsFragmentArgs by navArgs()

    override fun getViewModelClass() = DetailsViewModel::class.java

    override fun getViewBinding() = FragmentDetailsBinding.inflate(layoutInflater)

    override fun setupViews() = with(binding) {
        viewModel.extractArgs(args)

        viewModel.airport?.let {
            tvTitle.text = it.airportName
            tvCountryRegion.text = "${it.country.countryName}, ${it.region.regionName}"
            tvLat.text = "${it.location.latitude.toString()}°"
            tvLong.text = "${it.location.longitude.toString()}°"
            tvTimezone.text = it.city.timeZoneName
        }

        btnBack.setOnClickListener { findNavController().navigateUp() }
    }
}