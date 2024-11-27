package com.example.to_do.util

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.to_do.R
import com.example.to_do.presentation.activity.HomeActivity
import com.example.to_do.util.Constants.GONE
import com.example.to_do.util.Constants.TODAY_FORMAT
import com.example.to_do.util.Constants.VISIBLE
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textview.MaterialTextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

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
    Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.startHomeActivity() {
    val intent = Intent(this.activity, HomeActivity::class.java)
    startActivity(intent)
    requireActivity().finish()
}

fun getCurrentDate(materialTextView: MaterialTextView) {
    val currentDate = Calendar.getInstance().time
    val formatter = SimpleDateFormat(TODAY_FORMAT, Locale.ENGLISH)
    materialTextView.text = formatter.format(currentDate)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}