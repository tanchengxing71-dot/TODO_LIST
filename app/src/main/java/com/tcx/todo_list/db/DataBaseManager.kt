package com.tcx.todo_list.db

import android.content.Context
import androidx.room.Room

object DatabaseManager {
    private var db: AppDatabase? = null

    fun init(context: Context) {
        if (db == null) {
            db = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "todo.db"
            ).fallbackToDestructiveMigration(false)
                .build()
        }
    }

    fun getDb(): AppDatabase = db!!
}
