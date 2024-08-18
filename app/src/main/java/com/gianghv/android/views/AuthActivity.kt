package com.gianghv.android.views

import android.content.Context
import com.gianghv.android.base.BaseActivity
import com.gianghv.android.databinding.ActivityAuthBinding
import com.gianghv.android.repository.api.FirebaseListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding>() {
    override fun createBinding() = ActivityAuthBinding.inflate(layoutInflater)

    override val context: Context
        get() = this@AuthActivity


}
