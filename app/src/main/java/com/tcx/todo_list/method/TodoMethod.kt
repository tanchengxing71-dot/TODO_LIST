package com.tcx.todo_list.method

import android.util.Log
import android.widget.Toast
import com.tcx.todo_list.MethodHandler
import com.tcx.todo_list.db.DatabaseManager
import com.tcx.todo_list.db.enity.ToDo
import com.tcx.todo_list.db.enity.toToDo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

object TodoMethod {

    /**
     * 插入 ToDo
     */
    val insertToDoHandler: MethodHandler = { args, result, context ->


        val todo = args.toToDo()
        val rowId = DatabaseManager.getDb().todoDao().insert(todo)

        withContext(Dispatchers.Main) {
            if (rowId > 0) {
                Toast.makeText(context, "Insert Success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Insert Failed", Toast.LENGTH_SHORT).show()
            }
            result?.success(rowId)
            Log.d("tcx_test_insert", "args=$args rowId=$rowId")
        }
    }

    /**
     * 更新 ToDo
     * 必须传入 id，否则无法更新
     */
    val updateToDoHandler: MethodHandler = { args, result, context ->
        val todo = args.toToDo()

        val rows = DatabaseManager.getDb().todoDao().update(todo)

        withContext(Dispatchers.Main) {
            if (rows > 0) {
                Toast.makeText(context, "Update Success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Update Failed", Toast.LENGTH_SHORT).show()
            }
            result?.success(rows)
            Log.d("tcx_test_update", "args=$args rows=$rows")
        }
    }


    /**
     * 删除 ToDo
     */
    val deleteToDoHandler: MethodHandler = { args, result, context ->
        val todo = args.toToDo()

        val rows = DatabaseManager.getDb().todoDao().delete(todo)

        withContext(Dispatchers.Main) {
            if (rows > 0) {
                Toast.makeText(context, "Delete Success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Delete Failed", Toast.LENGTH_SHORT).show()
            }
            result?.success(rows)
            Log.d("tcx_test_delete", "args=$args rows=$rows")
        }
    }


    /**
     * 查询全部 ToDo
     */
    val queryAllToDoHandler: MethodHandler = { _, result, context ->

        val list = DatabaseManager.getDb().todoDao().getAll()

        withContext(Dispatchers.Main) {
            Toast.makeText(context, "Query All Success", Toast.LENGTH_SHORT).show()
            result?.success(Json.encodeToString(ListSerializer(ToDo.serializer()), list))
            Log.d("tcx_test_query_all", "size=${list.size}")
        }
    }


    /**
     * 按类型查询，例如 0-工作 1-学习 2-生活
     */
    val queryToDoByTypeHandler: MethodHandler = { args, result, context ->
        val type = args["type"] as Int

        val list = DatabaseManager.getDb().todoDao().getByType(type)

        withContext(Dispatchers.Main) {
            Toast.makeText(context, "Query By Type Success", Toast.LENGTH_SHORT).show()
            result?.success(Json.encodeToString(ListSerializer(ToDo.serializer()), list))
            Log.d("tcx_test_query_type", "type=$type size=${list.size}")
        }
    }
}