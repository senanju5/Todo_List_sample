package com.example.todo_list_sample.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class ToDo(
    @PrimaryKey(autoGenerate = true)val id: Int?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo("note") val note: String,
    @ColumnInfo("date") val date: String
)
