package com.example.to_do.presentation.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.to_do.R
import com.example.to_do.base.BaseFragment
import com.example.to_do.databinding.FragmentHomeBinding
import com.example.to_do.domain.model.Task
import com.example.to_do.presentation.home.adapters.AdapterTasks
import com.example.to_do.presentation.home.viewmodel.TaskViewModel
import com.example.to_do.util.Constants.COMPLETE
import com.example.to_do.util.Constants.DELETE
import com.example.to_do.util.Constants.UPDATE

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var taskViewModel : TaskViewModel
    private var adapterTasks: AdapterTasks? = null

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        taskViewModel = ViewModelProvider(requireActivity())[TaskViewModel::class.java]
        setupRecyclerView()
    }

    override fun onClicks() {
        adapterTasks = AdapterTasks { task, action ->
            taskOperation(task, action)
        }
        binding.apply {
            createTask.setOnClickListener {
                findNavController().navigate (
                    R.id.action_navigation_home_to_createTaskFragment
                )
            }
        }
    }

    private fun setupRecyclerView() {
        binding.tasksRecyclerView.apply {
            setHasFixedSize(true)
            adapter = adapterTasks
        }
        activity?.let {
            taskViewModel.getAllTasks().observe(viewLifecycleOwner) { task ->
                adapterTasks?.differ?.submitList(task)
            }
        }
    }

    private fun taskOperation(task: Task, action: String) {
        when (action) {
            DELETE -> taskViewModel.deleteTask(task)
            COMPLETE -> TODO()
            UPDATE -> taskViewModel.updateTask(task)
        }
    }

}