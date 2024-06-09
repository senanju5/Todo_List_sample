package com.example.todo_list_sample.data.repository

import android.content.Context
import com.example.todo_list_sample.data.database.model.ToDo
import com.example.todo_list_sample.data.model.ToDoModel
import com.example.todo_list_sample.data.offlinedatasource.OfflineDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ToDoRepository(private val offlineDataSource: OfflineDataSource = OfflineDataSource()) {
    suspend fun getToDos(context: Context): Flow<List<ToDoModel>> =
        offlineDataSource.getToDos(context).map { toDos ->
            toDos.map {
                ToDoModel(it.id, it.title, it.note, it.date)
        }
}


    suspend fun insertToDo(toDo: ToDoModel, context: Context) {
        val toDoOffline = ToDo(toDo.id, toDo.title, toDo.note, toDo.date)
        offlineDataSource.insertToDo(toDoOffline, context)
    }
}