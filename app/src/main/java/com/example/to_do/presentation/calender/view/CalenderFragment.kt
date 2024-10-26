package com.example.to_do.presentation.calender.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.to_do.base.BaseFragment
import com.example.to_do.databinding.FragmentCalenderBinding

class CalenderFragment : BaseFragment<FragmentCalenderBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCalenderBinding {
        return FragmentCalenderBinding.inflate(inflater, container, false)
    }

    override fun initialize() {  }

    override fun onClicks() {
        binding.apply {  }
    }

}