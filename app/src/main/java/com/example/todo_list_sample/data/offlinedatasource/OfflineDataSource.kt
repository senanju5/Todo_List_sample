package com.example.todo_list_sample.data.offlinedatasource

import android.content.Context
import com.example.todo_list_sample.data.database.ToDoDataBase
import com.example.todo_list_sample.data.model.ToDo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class OfflineDataSource() {
        suspend fun getToDos(context: Context): List<ToDo> {
        return   ToDoDataBase.getDataBae(context).getToDoDao().getAllToDo()
    }
    suspend fun insertToDo(toDo: ToDo, context: Context) {
        ToDoDataBase.getDataBae(context).getToDoDao().insert(toDo)

    }

}