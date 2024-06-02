package com.example.todo_list_sample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list_sample.data.model.ToDoModel
import com.example.todo_list_sample.databinding.TodoListItemViewBinding

class ToDoListAdapter():ListAdapter<ToDoModel, ToDoListAdapter.ToDoViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK  = object : DiffUtil.ItemCallback<ToDoModel>() {
            override fun areItemsTheSame(oldItem: ToDoModel, newItem: ToDoModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ToDoModel, newItem: ToDoModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ToDoViewHolder(private val binding: TodoListItemViewBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(toDo: ToDoModel) {
            binding.headerText.text = toDo.title
            binding.descriptionText.text = toDo.note
            binding.dateText.text= toDo.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding =
            TodoListItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

