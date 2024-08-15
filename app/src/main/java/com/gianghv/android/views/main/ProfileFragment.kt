package com.gianghv.android.views.main

import com.gianghv.android.R
import com.gianghv.android.base.BaseFragment
import com.gianghv.android.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override val layoutRes: Int
        get() = R.layout.fragment_profile

    override fun init() {
    }

    override fun setUp() {
    }
}
