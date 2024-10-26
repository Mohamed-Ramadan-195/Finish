package com.example.to_do.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.to_do.R
import com.example.to_do.data.local.TaskDatabase
import com.example.to_do.data.repository.TaskRepositoryImpl
import com.example.to_do.databinding.ActivityHomeBinding
import com.example.to_do.presentation.home.viewmodel.TaskViewModel
import com.example.to_do.presentation.home.viewmodel.TaskViewModelFactory
import com.example.to_do.util.Constants.GONE
import com.example.to_do.util.Constants.VISIBLE
import com.example.to_do.util.visibilityBottomNavigation
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController
    lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewModel()
        setNavHostFragment()
        setupBottomNavigationBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    private fun setNavHostFragment() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun setUpViewModel() {
        val taskRepositoryImpl = TaskRepositoryImpl(TaskDatabase(this))
        val viewModelProviderFactory = TaskViewModelFactory(application, taskRepositoryImpl)
        taskViewModel = ViewModelProvider(
            this,
            viewModelProviderFactory
        )[TaskViewModel::class.java]
    }

    private fun setupBottomNavigationBar() {
        val bottomNavigationView = binding.bottomNavigationView
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
            R.id.createTaskFragment,
            R.id.createCategoryFragment -> visibilityBottomNavigation(bottomNavigationView, GONE)
        }
    }

    private fun showBottomNavigation (
        bottomNavigationView: BottomNavigationView,
        navDestination: NavDestination
    ) {
        when (navDestination.id) {
            R.id.navigation_home,
            R.id.navigation_calender,
            R.id.navigation_search,
            R.id.navigation_profile -> visibilityBottomNavigation(bottomNavigationView, VISIBLE)
        }
    }

    @Deprecated("This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
    override fun onBackPressed() {
        if (!navController.popBackStack()) {
            super.onBackPressed()
        }
    }

    private fun setSystemBars() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updatePadding(left = systemBars.left, right = systemBars.right, top = systemBars.top)
            insets
        }
    }
}