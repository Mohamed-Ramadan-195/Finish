package com.example.to_do.presentation.onboarding.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.to_do.presentation.onboarding.adapters.ViewPagerAdapter
import com.example.to_do.base.BaseFragment
import com.example.to_do.databinding.FragmentViewPagerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewPagerFragment : BaseFragment<FragmentViewPagerBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentViewPagerBinding {
        return FragmentViewPagerBinding.inflate(inflater, container, false)
    }

    override fun initialize() = handleViewPager()

    override fun onClicks() {}

    private fun handleViewPager() {
        val fragmentList = arrayListOf(FirstScreenFragment(), SecondScreenFragment(), ThirdScreenFragment())
        val adapter = ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)
        binding.viewPager2.adapter = adapter
    }

}