package com.gianghv.android.views.main

import com.gianghv.android.R
import com.gianghv.android.base.BaseFragment
import com.gianghv.android.databinding.FragmentProjectBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectFragment : BaseFragment<FragmentProjectBinding>() {

    override val layoutRes: Int
        get() = R.layout.fragment_project

    override fun init() {
    }

    override fun setUp() {
    }
}
