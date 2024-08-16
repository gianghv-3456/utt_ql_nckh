package com.gianghv.android.views.main

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.gianghv.android.R
import com.gianghv.android.base.BaseFragment
import com.gianghv.android.databinding.FragmentHistoryBinding
import com.gianghv.android.views.common.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHistoryBinding, AuthViewModel>() {
    override val viewModel: AuthViewModel by viewModels()

    override val layoutRes: Int
        get() = R.layout.fragment_history

    override fun init() {
    }

    override fun setUp() {
    }

    override fun initData() {

    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentHistoryBinding {
        return FragmentHistoryBinding.inflate(inflater)
    }
}
