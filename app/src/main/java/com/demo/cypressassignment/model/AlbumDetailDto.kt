package com.demo.cypressassignment.model

data class AlbumDetailDto(
    var userId: Int? = 0,
    var id: Int? = 0,
    var title: String? = "",
    var list: List<PhotoDetail>? = listOf()
)

fun AlbumDetailDto.toPhotoDetail(): PhotoDetail {
    return PhotoDetail(
        photoId = id,
        albumTitle = title,
        albumId = id,
        userId = userId
    )
}

