package com.example.to_do.presentation.home.view

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.to_do.R
import com.example.to_do.base.BaseFragment
import com.example.to_do.databinding.FragmentHomeBinding
import com.example.to_do.domain.model.Task
import com.example.to_do.presentation.home.adapters.AdapterTasks
import com.example.to_do.presentation.home.viewmodel.HomeViewModel
import com.example.to_do.util.Constants.COMPLETE
import com.example.to_do.util.Constants.DELETE
import com.example.to_do.util.Constants.UPDATE
import com.example.to_do.util.getCurrentDate
import com.example.to_do.util.showToast
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val homeViewModel : HomeViewModel by viewModels()
    private var adapterTasks : AdapterTasks? = null

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        binding.today.text = getCurrentDate()
        homeViewModel.getAllTasks()
        setupTasksRecyclerView()
    }

    override fun onClicks() {
        binding.apply {
            dashboard.setOnClickListener {
                findNavController().navigate(R.id.action_navigation_home_to_dashboardFragment)
            }
            menu.setOnClickListener { view ->
                createPopupMenu(view)
            }
        }
    }

    private fun createPopupMenu(view: View) {
        val popupMenu = PopupMenu(requireActivity(), view)
        popupMenu.inflate(R.menu.home_options_menu)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.showCategory -> {
                    showToast("category")
                    true
                }
                R.id.showCompleted -> {
                    showToast("completed")
                    true
                }
                R.id.sort -> {
                    showToast("sort")
                    true
                }
                else -> { false }
            }
        }
        popupMenu.show()
    }

    private fun setupTasksRecyclerView() {
        homeViewModel.getAllTasksLiveData.observe(viewLifecycleOwner) {
            val data = it as ArrayList<Task>
            adapterTasks = AdapterTasks(data) { task ->
                homeViewModel.deleteTask(task)
                homeViewModel.getAllTasks()
            }
            binding.tasksRecyclerView.adapter = adapterTasks
        }
    }

    private fun taskOperation(task: Task, action: String) {
        when (action) {
            DELETE -> showAlertDialog(task, DELETE)
            COMPLETE -> showAlertDialog(task, COMPLETE)
            UPDATE -> TODO()
        }
    }

    private fun showAlertDialog (task: Task, action: String) {
        val dialog = Dialog(requireActivity())
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(false)
        }

        when (action) {
            DELETE -> {
                dialog.setContentView(R.layout.custom_dialog_delete)
                val cancel : MaterialButton = dialog.findViewById(R.id.cancelButton)
                val delete : MaterialButton = dialog.findViewById(R.id.deleteButton)
                cancel.setOnClickListener { dialog.dismiss() }
                delete.setOnClickListener {
                    homeViewModel.deleteTask(task)
                    showToast(getString(R.string.task_deleted_successfully))
                    dialog.dismiss()
                }
            }
            COMPLETE -> {
                dialog.setContentView(R.layout.custom_dialog_complete)
                val cancel : MaterialButton = dialog.findViewById(R.id.cancelButton)
                val complete : MaterialButton = dialog.findViewById(R.id.completeButton)
                cancel.setOnClickListener { dialog.dismiss() }
                complete.setOnClickListener { TODO() }
            }
        }
        dialog.show()
    }

}