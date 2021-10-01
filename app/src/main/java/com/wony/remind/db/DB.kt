package com.wony.remind.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(version = 1,entities = [RemindData.Item::class])
abstract class DB: RoomDatabase() {

    abstract fun remindDao(): RemindData.DAO

    companion object {
        private val dbName = "remind.db"
        private var INSTANCE: DB? = null
        private var context: Context? = null

        fun getInstance(context: Context): DB {
            this.context = context
            return INSTANCE ?: synchronized(DB::class) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    DB::class.java,
                    dbName
                )
                    .build().also { INSTANCE = it }
            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}