package com.example.androidudemycoupon.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.androidudemycoupon.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container)
            ?.findNavController() ?: throw Exception("CAN NOT FIND NAVCONTROLLER")
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.searchFragment, R.id.courseFragment, R.id.settingFragment))

        findViewById<BottomNavigationView>(R.id.bottom_nav_view).setupWithNavController(
            navController
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        supportActionBar?.hide()

    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}