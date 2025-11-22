package com.tcx.todo_list.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tcx.todo_list.db.enity.ToDo

@Dao
interface ToDoDao {
    // 增
    @Insert
    suspend fun insert(todo: ToDo): Long

    // 改
    @Update
    suspend fun update(todo: ToDo): Int

    // 删
    @Delete
    suspend fun delete(todo: ToDo): Int

    // 查所有
    @Query("SELECT * FROM todo")
    suspend fun getAll(): List<ToDo>

    // 条件查询
    @Query("SELECT * FROM todo WHERE type = :type")
    suspend fun getByType(type: Int): List<ToDo>
}