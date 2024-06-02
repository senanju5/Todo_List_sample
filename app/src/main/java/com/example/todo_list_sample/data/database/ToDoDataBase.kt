package com.example.todo_list_sample.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ToDo::class], version = 1)
abstract class ToDoDataBase : RoomDatabase() {
    abstract fun getToDoDao(): ToDoDao

    companion object {
        @Volatile
        private var INSTANCE: ToDoDataBase? = null

        fun getDataBae(context: Context): ToDoDataBase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoDataBase::class.java,
                    "todo_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}