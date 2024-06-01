package com.example.todo_list_sample.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_list_sample.data.model.ToDo
import com.example.todo_list_sample.domain.ToDoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val toDoUseCase: ToDoUseCase = ToDoUseCase() ): ViewModel() {
    var toDoList = MutableLiveData<List<ToDo>>()

    fun getToDoList(context: Context) {
        viewModelScope.launch {
            toDoUseCase.getToDoList(context).collect{
                toDoList.value = it
            }
        }
    }

      fun  insertToDo(context: Context, toDo: ToDo) {
         viewModelScope.launch {
             toDoUseCase.insertToDo( toDo, context)
         }
    }
}