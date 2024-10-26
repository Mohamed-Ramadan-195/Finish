package com.example.to_do.presentation.profile.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.to_do.base.BaseFragment
import com.example.to_do.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater, container, false)
    }

    override fun initialize() {  }

    override fun onClicks() {
        binding.apply {

        }
    }
}