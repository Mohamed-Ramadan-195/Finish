package com.example.to_do.presentation.onboarding.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.to_do.databinding.FragmentSecondScreenBinding
import com.example.to_do.util.declareViewPager
import com.example.to_do.util.onBoardingFinished
import com.example.to_do.util.startHomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondScreenFragment : Fragment() {

    private var _binding: FragmentSecondScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSecondScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClicks()
    }

    private fun onClicks() {
        binding.apply {
            nextButton.setOnClickListener { declareViewPager(2) }
            skipButton.setOnClickListener {
                onBoardingFinished()
                startHomeActivity()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}