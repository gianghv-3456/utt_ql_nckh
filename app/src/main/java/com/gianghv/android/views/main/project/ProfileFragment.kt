package com.gianghv.android.views.main.project

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.gianghv.android.R
import com.gianghv.android.base.BaseFragment
import com.gianghv.android.databinding.FragmentProfileBinding
import com.gianghv.android.views.common.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, AuthViewModel>() {
    override val viewModel: AuthViewModel by viewModels()

    override val layoutRes: Int
        get() = R.layout.fragment_profile

    override fun init() {
    }

    override fun setUp() {
    }

    override fun initData() {

    }

    override fun inflateViewBinding(inflater: LayoutInflater) = FragmentProfileBinding.inflate(inflater)
}
