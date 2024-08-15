package com.gianghv.android.views.main

import com.gianghv.android.R
import com.gianghv.android.base.BaseFragment
import com.gianghv.android.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {

    override val layoutRes: Int
        get() = R.layout.fragment_history

    override fun init() {
    }

    override fun setUp() {
    }
}
