package com.example.todo_list_sample.domain

import android.content.Context
import com.example.todo_list_sample.data.model.ToDo
import com.example.todo_list_sample.data.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow

class ToDoUseCase(private val repository: ToDoRepository = ToDoRepository()) {
    suspend fun getToDoList(context: Context): Flow<List<ToDo>> {
        return repository.getToDos(context)
    }

    suspend fun insertToDo(toDo: ToDo, context: Context) {
        return repository.insertToDo(toDo, context)
    }
}