package com.example.todo_list_sample.data.offlinedatasource

import android.content.Context
import com.example.todo_list_sample.data.database.ToDoDataBase
import com.example.todo_list_sample.data.database.ToDo
import kotlinx.coroutines.flow.Flow

class OfflineDataSource() {
        suspend fun getToDos(context: Context): Flow<List<ToDo>> {
        return   ToDoDataBase.getDataBae(context).getToDoDao().getAllToDo()
    }
    suspend fun insertToDo(toDo: ToDo, context: Context) {
        ToDoDataBase.getDataBae(context).getToDoDao().insert(toDo)

    }

}