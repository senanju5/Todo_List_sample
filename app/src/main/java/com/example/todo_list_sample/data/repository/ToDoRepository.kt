package com.example.todo_list_sample.data.repository

import android.content.Context
import com.example.todo_list_sample.data.database.ToDoDataBase
import com.example.todo_list_sample.data.model.ToDo
import com.example.todo_list_sample.data.offlinedatasource.OfflineDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ToDoRepository(private val offlineDataSource: OfflineDataSource = OfflineDataSource()) {
    suspend fun getToDos(context: Context): Flow<List<ToDo>>{
        return offlineDataSource.getToDos(context)
    }

    suspend fun insertToDo(toDo: ToDo, context: Context) {
        offlineDataSource.insertToDo(toDo, context)
    }
}