package com.example.airports.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.airports.domain.model.Resource
import com.example.airports.domain.model.Status

abstract class BaseFragment<VM : ViewModel, VB : ViewBinding> : Fragment() {

    protected lateinit var viewModel: VM
    protected abstract fun getViewModelClass(): Class<VM>

    protected lateinit var binding: VB
    protected abstract fun getViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(this, defaultViewModelProviderFactory).get(getViewModelClass())
        binding = getViewBinding()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeDate()
    }

    open fun setupViews() {}

    open fun observeDate() {}

    open fun <T> observeDataFlow(
        liveData: LiveData<Resource<T>>,
        onSuccess: (T) -> Unit,
        onError: (String) -> Unit,
        onLoading: () -> Unit
    ) {
        liveData.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> onLoading()
                Status.SUCCESS -> it.data?.let { onSuccess(it) }
                Status.ERROR -> onError(it.message.orEmpty())
            }
        }
    }
}