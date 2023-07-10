package com.bunooboi.stadice.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bunooboi.stadice.Task

@Dao
interface TaskDao {
    @Query("select * from tasks")
    fun getAll(): LiveData<MutableList<Task>>

    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)
}