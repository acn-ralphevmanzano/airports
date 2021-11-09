package com.example.airports.ui.details

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.airports.R
import com.example.airports.databinding.FragmentDetailsBinding
import com.example.airports.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<DetailsViewModel, FragmentDetailsBinding>() {

    private val args: DetailsFragmentArgs by navArgs()

    override fun getViewModelClass() = DetailsViewModel::class.java

    override fun getViewBinding() = FragmentDetailsBinding.inflate(layoutInflater)

    override fun setupViews() = with(binding) {
        viewModel.extractArgs(args)
        btnBack.setOnClickListener { findNavController().navigateUp() }
    }

    override fun observeDate() = with(binding) {
        viewModel.airport.observe(viewLifecycleOwner) {
            tvTitle.text = it.airportName
            tvCountryRegion.text =
                getString(R.string.country_region, it.country.countryName, it.region.regionName)
            tvLat.text = getString(R.string.coordinate, it.location.latitude)
            tvLong.text = getString(R.string.coordinate, it.location.longitude)
            tvTimezone.text = it.city.timeZoneName
        }
    }
}