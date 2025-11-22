package com.tcx.todo_list

import android.app.Application
import com.tcx.todo_list.db.DatabaseManager
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor


class TodoListApplication : Application() {
    lateinit var flutterEngine : FlutterEngine
    override fun onCreate() {
        super.onCreate()
        DatabaseManager.init(this)
        flutterEngine = FlutterEngine(this)
        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )
        // Cache the FlutterEngine to be used by FlutterActivity.
        FlutterEngineCache
            .getInstance()
            .put("flutter_boost_default_engine", flutterEngine)
        RouterMap.setupFlutterBoost(this)
    }
}
