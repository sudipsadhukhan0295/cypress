package com.demo.cypressassignment.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.cypressassignment.model.AlbumDetailDto
import com.demo.cypressassignment.network.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    val album: MutableLiveData<List<AlbumDetailDto>> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()

    private fun getOfflinePhotos() {
        viewModelScope.launch {
            mainRepository.getOfflinePhotos().let { response ->
                when (response) {
                    is ApiResult.Error -> {
                        loading.value = false
                    }
                    ApiResult.InProgress -> {}
                    is ApiResult.Success -> {
                        loading.value = false
                        val data = response.data
                        val albumList = arrayListOf<AlbumDetailDto>()
                        val map = data.groupBy { x -> x.albumId }
                        map.forEach { list ->
                            val albumDetail = AlbumDetailDto()
                            albumDetail.id = list.key
                            if (list.value.isNotEmpty()) {
                                albumDetail.title = list.value[0].albumTitle
                                albumDetail.userId = list.value[0].userId
                                albumDetail.list = list.value
                            }
                            albumList.add(albumDetail)
                        }
                        albumList.sortBy { x -> x.id }
                        album.value = albumList
                    }
                }
            }
        }
    }

    fun getPhotos() {
        loading.value = true

        viewModelScope.launch {
            val job = async { mainRepository.getAlbumDetail() }
            when (val result = job.await()) {
                is ApiResult.Error -> {
                    getOfflinePhotos()
                }
                ApiResult.InProgress -> {

                }
                is ApiResult.Success -> {
                    val list = result.data
                    list.sortedBy { s -> s.id }
                    list.map {
                        async {
                            mainRepository.getPhotos(it).let { response ->
                                when (response) {
                                    is ApiResult.Error -> {
                                        getOfflinePhotos()
                                    }
                                    ApiResult.InProgress -> {

                                    }
                                    is ApiResult.Success -> {
                                        val photos = response.data
                                        photos.forEach { photoDetail ->
                                            photoDetail.albumTitle = it.title
                                            photoDetail.albumId = it.id
                                            photoDetail.userId = it.userId
                                        }
                                        withContext(Dispatchers.IO) {
                                            mainRepository.setPhotos(photos)
                                        }
                                        it.list = photos
                                    }
                                }
                            }
                        }
                    }.awaitAll()
                    getOfflinePhotos()
                }
            }
        }
    }
}