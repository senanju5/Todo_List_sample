package com.example.todo_list_sample.data.repository

import android.content.Context
import android.util.Log
import com.example.todo_list_sample.data.database.ToDo
import com.example.todo_list_sample.data.model.ToDoModel
import com.example.todo_list_sample.data.offlinedatasource.OfflineDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ToDoRepository(private val offlineDataSource: OfflineDataSource = OfflineDataSource()) {
    suspend fun getToDos(context: Context): Flow<List<ToDoModel>> = flow{

        var toDoModels: MutableList<ToDoModel> = mutableListOf()
        offlineDataSource.getToDos(context).collect{
            Log.d("getTodos before size", "getToDos: ${it.size}")

            Log.d("getTodos before", "getToDos: $toDoModels")

            for (toDo in it) {
                toDoModels.add(ToDoModel(toDo.id, toDo.title, toDo.note, toDo.date))

            }
            Log.d("getTodos after", "getToDos: $toDoModels")

            emit(toDoModels.distinct())
        }
    }.flowOn(Dispatchers.IO)

    suspend fun insertToDo(toDo: ToDoModel, context: Context) {
        val toDoOffline = ToDo(toDo.id, toDo.title, toDo.note, toDo.date)
        offlineDataSource.insertToDo(toDoOffline, context)
    }
}