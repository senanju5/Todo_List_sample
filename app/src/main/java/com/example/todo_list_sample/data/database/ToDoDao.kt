package com.example.todo_list_sample.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todo_list_sample.data.model.ToDo

@Dao
interface ToDoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insert(todo: ToDo)

    @Query("SELECT * FROM todo_table order by id ASC")
     suspend fun getAllToDo(): List<ToDo>
}