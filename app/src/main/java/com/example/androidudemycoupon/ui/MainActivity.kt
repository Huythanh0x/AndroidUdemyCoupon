package com.example.androidudemycoupon.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.androidudemycoupon.R
import com.example.androidudemycoupon.ui.setting.SettingViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    val settingViewModel: SettingViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        settingViewModel.isDarkMode.observe(this) {
            it?.let {
                if (it) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    delegate.applyDayNight()
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    delegate.applyDayNight()
                }
            }
        }
        navController = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container)
            ?.findNavController() ?: throw Exception("CAN NOT FIND NAVCONTROLLER")
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.searchFragment, R.id.courseFragment, R.id.settingFragment
            )
        )

        findViewById<BottomNavigationView>(R.id.bottom_nav_view).setupWithNavController(
            navController
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        supportActionBar?.hide()

    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }

    fun showBottomNavigation(show: Boolean) {
        if (show) {
            findViewById<BottomNavigationView>(R.id.bottom_nav_view).visibility = View.VISIBLE
        } else {
            findViewById<BottomNavigationView>(R.id.bottom_nav_view).visibility = View.GONE
        }
    }
}