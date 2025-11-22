package com.tcx.todo_list.db.enity

import androidx.room.Entity
import androidx.room.PrimaryKey

@kotlinx.serialization.Serializable
@Entity(tableName = "todo")
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val content: String,
    val time: String,
    val type: Int,//0 工作，1 学习，2 生活
    val status: Int,//0 未完成，1 已完成
)

fun Map<String, Any?>.toToDo(): ToDo {
    return ToDo(
        id = (this["id"] as? Int) ?: 0,                     // 默认 0 → Room 自动生成
        title = this["title"] as? String ?: "",             // 默认空字符串
        content = this["content"] as? String ?: "",         // 默认空字符串
        time = this["time"] as? String ?: "",               // 默认空字符串
        type = when (val t = this["type"]) {
            is Int -> t                                     // 正常 Int
            is Number -> t.toInt()                          // Flutter 有时会传 double
            else -> 0                                       // 默认 0（工作）
        },
        status = (this["status"] as? Int) ?: 0,
    )
}



