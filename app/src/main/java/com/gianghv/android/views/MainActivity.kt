package com.gianghv.android.views

import android.content.Context
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.gianghv.android.R
import com.gianghv.android.base.BaseActivity
import com.gianghv.android.databinding.ActivityMainBinding
import com.gianghv.android.repository.api.FirebaseListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun createBinding() = ActivityMainBinding.inflate(layoutInflater)

    override val context: Context
        get() = this@MainActivity

    override fun setUp() {
        super.setUp()
        FirebaseListener.listenFirebase()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment2) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
    }

}
