package com.gianghv.android.views

import android.content.Context
import androidx.activity.viewModels
import com.gianghv.android.base.BaseActivity
import com.gianghv.android.databinding.ActivityAuthBinding
import com.gianghv.android.views.common.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding, AuthViewModel>() {
    override val viewModel: AuthViewModel by viewModels()

    override fun createBinding() = ActivityAuthBinding.inflate(layoutInflater)

    override val context: Context
        get() = this@AuthActivity
}
