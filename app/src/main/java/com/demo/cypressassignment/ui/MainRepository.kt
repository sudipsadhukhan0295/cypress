package com.demo.cypressassignment.ui

import com.demo.cypressassignment.database.DbDataSource
import com.demo.cypressassignment.model.AlbumDetailDto
import com.demo.cypressassignment.model.PhotoDetail
import com.demo.cypressassignment.network.ApiResult
import com.demo.cypressassignment.network.NetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val dbDataSource: DbDataSource
) {
    private val ioDispatchers = Dispatchers.IO

    suspend fun getAlbumDetail(): ApiResult<List<AlbumDetailDto>> {
        return withContext(ioDispatchers) {
            try {
                return@withContext ApiResult.Success(networkDataSource.getAlbumList())
            } catch (e: Exception) {
                return@withContext ApiResult.Error(e)
            }
        }
    }

    suspend fun getPhotos(albumDetail: AlbumDetailDto): ApiResult<List<PhotoDetail>> {
        return withContext(ioDispatchers) {
            try {
                val response = networkDataSource.getPhotoList(albumDetail.id ?: 0)
                response.forEach {
                    it.albumId = albumDetail.id
                    it.albumTitle = albumDetail.title
                }
                return@withContext ApiResult.Success(response)
            } catch (e: Exception) {
                return@withContext ApiResult.Error(e)
            }
        }
    }

    suspend fun setPhotos(list: List<PhotoDetail>) {
        withContext(ioDispatchers) {
            dbDataSource.insertPhotos(list)
        }
    }

    suspend fun getOfflinePhotos(): ApiResult<List<PhotoDetail>> {
        return withContext(ioDispatchers) {
            try {
                return@withContext ApiResult.Success(dbDataSource.getPhotos())
            } catch (e: Exception) {
                return@withContext ApiResult.Error(e)
            }
        }
    }
}