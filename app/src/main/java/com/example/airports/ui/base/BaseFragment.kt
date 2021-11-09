package com.example.airports.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.airports.R
import com.example.airports.domain.model.Resource
import com.example.airports.domain.model.Status
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<VM : ViewModel, VB : ViewBinding> : Fragment() {

    protected lateinit var viewModel: VM
    protected abstract fun getViewModelClass(): Class<VM>

    protected lateinit var binding: VB
    protected abstract fun getViewBinding(): VB

    protected var snackbar: Snackbar? = null

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

    fun <T> observeDataFlow(
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

    fun showSnackbar(
        message: String,
        isError: Boolean,
        @BaseTransientBottomBar.Duration length: Int = Snackbar.LENGTH_INDEFINITE,
        onClickListener: (() -> Unit)? = null
    ) {
        snackbar?.dismiss()
        snackbar = Snackbar.make(binding.root, message, length)
        if (isError) {
            snackbar?.setBackgroundTint(getColor(binding.root.context, android.R.color.holo_red_light))
            snackbar?.setActionTextColor(getColor(binding.root.context, R.color.white))
        }
        onClickListener?.let { snackbar?.setAction("Retry") { it() } }
        snackbar?.show()
    }
}