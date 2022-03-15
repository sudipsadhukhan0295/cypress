package com.demo.cypressassignment.database

import com.demo.cypressassignment.database.DbDao
import com.demo.cypressassignment.model.PhotoDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DbDataSource @Inject constructor(private val dbDao: DbDao) {

    private val ioDispatcher = Dispatchers.IO

    suspend fun insertPhotos(photos: List<PhotoDetail>) {
        withContext(ioDispatcher) {
            dbDao.addPhotos(photos)
        }
    }

    suspend fun getPhotos():List<PhotoDetail> {
        return withContext(ioDispatcher) {
            return@withContext dbDao.getPhotos()
        }
    }
}