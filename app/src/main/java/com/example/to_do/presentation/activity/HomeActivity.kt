package com.example.to_do.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.to_do.R
import com.example.to_do.databinding.ActivityHomeBinding
import com.example.to_do.util.Constants.GONE
import com.example.to_do.util.Constants.VISIBLE
import com.example.to_do.util.visibilityBottomNavigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavHostFragment()
        setupBottomNavigationBar()
    }

    private fun setupNavHostFragment() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun setupBottomNavigationBar() {
        val bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.background = null
        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, navDestination, _ ->
            hideBottomNavigation(bottomNavigationView, navDestination)
            showBottomNavigation(bottomNavigationView, navDestination)
        }
    }

    private fun hideBottomNavigation (
        bottomNavigationView: BottomNavigationView,
        navDestination: NavDestination
    ) {
        when (navDestination.id) {
            R.id.dashboardFragment,
            R.id.splashFragment,
            R.id.viewPagerFragment,
            R.id.detailsFragment -> visibilityBottomNavigation(bottomNavigationView, GONE)
        }
    }

    private fun showBottomNavigation (
        bottomNavigationView: BottomNavigationView,
        navDestination: NavDestination
    ) {
        when (navDestination.id) {
            R.id.navigation_home,
            R.id.navigation_calender,
            R.id.navigation_create_task -> visibilityBottomNavigation(bottomNavigationView, VISIBLE)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    @Suppress("DEPRECATION")
    @Deprecated("This method has been deprecated in favor of using the\n      " +
            "{@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      " +
            "The OnBackPressedDispatcher controls how back button events are dispatched\n      " +
            "to one or more {@link OnBackPressedCallback} objects.")
    override fun onBackPressed() {
        if (!navController.popBackStack()) {
            super.onBackPressed()
        }
    }

}