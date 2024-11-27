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
import com.example.to_do.databinding.CustomDialogCompleteBinding
import com.example.to_do.databinding.CustomDialogDeleteBinding
import com.example.to_do.databinding.FragmentHomeBinding
import com.example.to_do.domain.model.Task
import com.example.to_do.presentation.home.adapters.AdapterTasks
import com.example.to_do.presentation.home.viewmodel.HomeViewModel
import com.example.to_do.util.Constants.COMPLETE
import com.example.to_do.util.Constants.DELETE
import com.example.to_do.util.Constants.UPDATE
import com.example.to_do.util.getCurrentDate
import com.example.to_do.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val homeViewModel : HomeViewModel by viewModels()
    private val adapterTasks : AdapterTasks by lazy { AdapterTasks() }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        getCurrentDate(binding.today)
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
        adapterTasks.onUserClick = object : AdapterTasks.OnUserClick {
            override fun onClick(task: Task, action: String) {
                taskOperation(task, action)
            }
        }
    }

    private fun createPopupMenu(view: View) {
        val popupMenu = PopupMenu(requireActivity(), view)
        popupMenu.apply {
            inflate(R.menu.home_options_menu)
            setOnMenuItemClickListener {
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
            show()
        }
    }

    private fun setupTasksRecyclerView() {
        homeViewModel.getAllTasksLiveData.observe(viewLifecycleOwner) {
            val data = it as ArrayList<Task>
            adapterTasks.tasksList = data
            binding.tasksRecyclerView.adapter = adapterTasks
        }
    }

    private fun taskOperation(task: Task, action: String) {
        when (action) {
            DELETE -> showAlertDialog(task, DELETE)
            COMPLETE -> showAlertDialog(task, COMPLETE)
            UPDATE -> findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToDetailsFragment(task.taskId))
        }
    }

    private fun showAlertDialog (task: Task, action: String) {
        val dialog = Dialog(requireActivity())
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(false)

            when (action) {
                DELETE -> {
                    val binding = CustomDialogDeleteBinding.inflate(layoutInflater)
                    setContentView(binding.root)
                    binding.apply {
                        cancelButton.setOnClickListener { dismiss() }
                        deleteButton.setOnClickListener {
                            homeViewModel.deleteTask(task)
                            showToast(getString(R.string.task_deleted_successfully))
                            dismiss()
                        }
                    }
                }
                COMPLETE -> {
                    val binding = CustomDialogCompleteBinding.inflate(layoutInflater)
                    setContentView(binding.root)
                    binding.apply {
                        cancelButton.setOnClickListener { dismiss() }
                        completeButton.setOnClickListener { TODO() }
                    }
                }
            }
            show()
        }
    }

}