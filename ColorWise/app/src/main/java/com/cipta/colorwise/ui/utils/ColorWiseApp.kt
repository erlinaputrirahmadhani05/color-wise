package com.cipta.colorwise.utils

import android.app.Application
import android.database.CursorWindow
import java.lang.reflect.Field

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        // Tingkatkan ukuran CursorWindow
        try {
            val field: Field = CursorWindow::class.java.getDeclaredField("sCursorWindowSize")
            field.isAccessible = true
            field.set(null, 1024 * 1024 * 10) // 10MB
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
