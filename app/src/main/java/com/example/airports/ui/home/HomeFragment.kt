package com.example.airports.ui.home

import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.airports.R
import com.example.airports.databinding.FragmentHomeBinding
import com.example.airports.domain.model.Airport
import com.example.airports.domain.model.Status
import com.example.airports.ui.base.BaseFragment
import com.example.airports.utils.Constants
import com.example.airports.utils.extensions.hide
import com.example.airports.utils.extensions.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    private lateinit var adapter: AirportsAdapter

    override fun getViewModelClass() = HomeViewModel::class.java

    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

    override fun setupViews() = with(binding) {
        adapter = AirportsAdapter()
        adapter.onItemClick = { navigateToDetails(it) }
        rv.adapter = adapter
    }

    override fun observeDate() = with(binding) {
        viewModel.airportsList.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> {
                    pb.show()
                    rv.hide()
                }
                Status.SUCCESS -> {
                    pb.hide()
                    rv.show()
                    if (!it.data.isNullOrEmpty()) adapter.submitList(it.data)
                }
                Status.ERROR -> {
                    Toast.makeText(binding.root.context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun navigateToDetails(airport: Airport) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(airport.toJson())
        findNavController().navigate(action)
    }
}