package com.demo.cypressassignment.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.cypressassignment.model.PhotoDetail

@Database(entities = [PhotoDetail::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dbDao(): DbDao

}