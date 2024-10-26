package com.example.to_do.presentation.search.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.to_do.base.BaseFragment
import com.example.to_do.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun initialize() {  }

    override fun onClicks() {
        binding.apply {

        }
    }
}