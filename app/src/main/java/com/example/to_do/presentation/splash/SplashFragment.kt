package com.example.to_do.presentation.splash

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.to_do.R
import com.example.to_do.base.BaseFragment
import com.example.to_do.databinding.FragmentSplashBinding
import com.example.to_do.util.Constants.SPLASH_TIME
import com.example.to_do.util.startHomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override fun getViewBinding (
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater, container, false)
    }

    override fun initialize() = handleSplash()

    override fun onClicks() {  }

    private fun handleSplash() {
        Handler(Looper.myLooper()!!).postDelayed ( {
            if (checkOnBoarding()) startHomeActivity()
            else findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
        }, SPLASH_TIME.toLong())
    }

    private fun checkOnBoarding(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences(getString(R.string.onBoarding), Context.MODE_PRIVATE)
        return sharedPref.getBoolean(getString(R.string.finished), false)
    }
}