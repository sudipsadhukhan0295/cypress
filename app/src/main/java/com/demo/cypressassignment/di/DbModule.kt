package com.demo.cypressassignment.di

import android.content.Context
import androidx.room.Room
import com.demo.cypressassignment.database.AppDatabase
import com.demo.cypressassignment.database.DbDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DbModule {
    @Provides
    fun provideDbDao(appDatabase: AppDatabase): DbDao {
        return appDatabase.dbDao()
    }


    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app-db").build()

    }
}