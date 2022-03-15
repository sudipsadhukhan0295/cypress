package com.demo.cypressassignment.network

import com.demo.cypressassignment.model.AlbumDetailDto
import com.demo.cypressassignment.model.PhotoDetail
import com.demo.cypressassignment.model.toPhotoDetail
import javax.inject.Inject

class NetworkDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getPhotoList(id: Int): List<PhotoDetail> {
        return apiService.getPhotos(id).map { it.toPhotoDetail() }
    }

    suspend fun getAlbumList(): List<AlbumDetailDto> {
        return apiService.getAlbums()
    }
}