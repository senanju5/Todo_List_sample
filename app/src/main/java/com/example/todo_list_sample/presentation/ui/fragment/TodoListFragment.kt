package com.example.todo_list_sample.presentation.ui.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.todo_list_sample.R
import com.example.todo_list_sample.ToDoListAdopter
import com.example.todo_list_sample.data.model.ToDo
import com.example.todo_list_sample.databinding.FragmentTodoListBinding
import com.example.todo_list_sample.databinding.TodoDialogLayoutBinding
import com.example.todo_list_sample.presentation.viewmodel.MainViewModel


class TodoListFragment : Fragment() {

    private lateinit var binding: FragmentTodoListBinding
    private val viewModel: MainViewModel by viewModels()
    private val todoAdapter: ToDoListAdopter by lazy {
        ToDoListAdopter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTodoListBinding.inflate(inflater, container, false)
        var view = binding.root
        binding.floatingActionButton.setOnClickListener {
            openDialog()
        }

        viewModel.getToDoList(requireContext())
        viewModel.toDoList.observe(viewLifecycleOwner) {
            todoAdapter.submitList(it)
            Toast.makeText(requireContext(), it.size.toString(), Toast.LENGTH_LONG).show()
        }
        setUpRecyclerView()

        return view
    }

    override fun onResume() {

        super.onResume()
    }

    private fun setUpRecyclerView() {
        binding.todoListView.layoutManager = LinearLayoutManager(requireContext())
        binding.todoListView.adapter = todoAdapter
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        binding.todoListView.addItemDecoration(dividerItemDecoration)
    }

    private fun openDialog() {
        val binding = TodoDialogLayoutBinding.inflate(
            LayoutInflater.from(requireContext()),
            view as ViewGroup,
            false
        )
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(binding.root)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        );

        binding.addButton.setOnClickListener {
            val title = binding.headerEditText.text.toString()
            val description = binding.descriptionEditText.text.toString()
            if (title.isNotEmpty() && description.isNotEmpty()){
                viewModel.insertToDo(requireContext(), ToDo(null,title, description))
                dialog.dismiss()
            } else {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_LONG).show()
            }
        }
        dialog.show()
    }

}