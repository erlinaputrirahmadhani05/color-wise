package com.cipta.colorwise.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [QuestionEntity::class, HasilTes::class, User::class], version = 5, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
    abstract fun hasilTesDao(): HasilTesDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .addMigrations(MIGRATION_1_3, MIGRATION_2_3, MIGRATION_3_3)
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

        // Migrasi dari versi 1 ke 2
        private val MIGRATION_1_3 = object : Migration(1, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Membuat tabel hasil_tes jika belum ada
                database.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS hasil_tes (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        totalPercobaan INTEGER NOT NULL,
                        benar INTEGER NOT NULL,
                        salah INTEGER NOT NULL
                    )
                    """.trimIndent()
                )
            }
        }

        // Migrasi dari versi 2 ke 3
        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Menambahkan kolom timestamp jika belum ada
                database.execSQL(
                    """
                    ALTER TABLE hasil_tes 
                    ADD COLUMN timestamp INTEGER NOT NULL DEFAULT 0
                    """.trimIndent()
                )
            }
        }

        private val MIGRATION_3_3 = object : Migration(3, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    """
            CREATE TABLE IF NOT EXISTS user_table (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                userName TEXT NOT NULL
            )
            """.trimIndent()
                )
            }
        }

    }
}