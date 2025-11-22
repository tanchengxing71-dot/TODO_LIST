package com.tcx.todo_list.db
import androidx.room.Database
import androidx.room.RoomDatabase
import com.tcx.todo_list.db.enity.ToDo

@Database(entities = [ToDo::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): ToDoDao
}
