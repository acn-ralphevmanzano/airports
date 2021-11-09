package com.example.airports.ui.home

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.airports.databinding.FragmentHomeBinding
import com.example.airports.domain.model.Airport
import com.example.airports.ui.base.BaseFragment
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
        rv.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    override fun observeDate() = with(binding) {
        observeDataFlow(
            viewModel.airportsResult,
            onLoading = {
                pb.show()
                rv.hide()
            },
            onSuccess = {
                pb.hide()
                rv.show()
                if (!it.isNullOrEmpty()) adapter.submitList(it)
            },
            onError = {
                pb.hide()
                rv.hide()
                showSnackbar(it.first, true) { viewModel.getAirports() }
            }
        )
    }

    private fun navigateToDetails(airport: Airport) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(airport.toJson())
        findNavController().navigate(action)
    }
}