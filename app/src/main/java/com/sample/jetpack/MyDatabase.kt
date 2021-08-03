package com.sample.jetpack

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    companion object {
        private const val databaseName = "db"

        @Volatile private var instance: MyDatabase? = null

        fun getInstance(ctx: Context): MyDatabase {
            val checkInstance = instance
            if (checkInstance != null) {
                return checkInstance
            }

            return synchronized(this) {
                if (instance != null) {
                    instance!!
                } else {
                    instance = Room.databaseBuilder(
                        ctx.applicationContext,
                        MyDatabase::class.java,
                        databaseName
                    ).build()
                    instance!!
                }
            }
        }
    }

    abstract fun studentDao(): StudentDao
}
