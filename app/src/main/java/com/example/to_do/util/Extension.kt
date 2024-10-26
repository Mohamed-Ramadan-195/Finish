package com.example.to_do.util

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.to_do.R
import com.example.to_do.util.Constants.GONE
import com.example.to_do.util.Constants.VISIBLE
import com.google.android.material.bottomnavigation.BottomNavigationView

fun Fragment.declareViewPager(currentItem: Int) {
    val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager2)
    viewPager?.currentItem = currentItem
}

fun Fragment.onBoardingFinished() {
    val sharedPref = requireActivity().getSharedPreferences(getString(R.string.onBoarding), Context.MODE_PRIVATE)
    val editor = sharedPref.edit()
    editor.putBoolean(getString(R.string.finished), true)
    editor.apply()
}

fun visibilityBottomNavigation (
    bottomNavigationView: BottomNavigationView,
    visibility: String
) {
    when (visibility) {
        VISIBLE -> bottomNavigationView.visibility = View.VISIBLE
        GONE -> bottomNavigationView.visibility = View.GONE
    }
}

fun Fragment.emptyEditText(editText: EditText) {
    editText.error = getString(R.string.required)
}

fun Fragment.showToast(message: String) {
    Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show()
}