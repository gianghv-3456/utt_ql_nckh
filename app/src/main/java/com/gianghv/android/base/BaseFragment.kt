package com.gianghv.android.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class BaseFragment<B : ViewBinding, V : BaseViewModel> : Fragment() {

    private var _binding: B? = null
    protected val binding get() = _binding!!

    protected abstract val viewModel: V

    abstract val layoutRes: Int

    abstract fun init()

    abstract fun setUp()

    abstract fun initData()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflateViewBinding(inflater)
        init()
        return binding.root
    }

    abstract fun inflateViewBinding(inflater: LayoutInflater): B

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
        initData()
    }

    fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun <T> collectLifecycleFlow(flow: Flow<T>, collect: (T) -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                flow.collect(collect)
            }
        }
    }
}
