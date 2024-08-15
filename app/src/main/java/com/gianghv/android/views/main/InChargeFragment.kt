package com.gianghv.android.views.main

import com.gianghv.android.R
import com.gianghv.android.base.BaseFragment
import com.gianghv.android.databinding.FragmentInChargeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InChargeFragment : BaseFragment<FragmentInChargeBinding>() {

    override val layoutRes: Int
        get() = R.layout.fragment_in_charge

    override fun init() {
    }

    override fun setUp() {
    }
}
