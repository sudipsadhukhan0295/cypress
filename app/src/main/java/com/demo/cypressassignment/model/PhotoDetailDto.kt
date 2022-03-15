package com.demo.cypressassignment.model

data class PhotoDetailDto(
    var id: Int? = 0,
    var title: String? = "",
    var albumId: Int? = 0,
    var albumTitle: String? = "",
    var userId: Int? = 0,
    var thumbnailUrl: String? = ""
)

fun PhotoDetailDto.toPhotoDetail():PhotoDetail{
    return PhotoDetail(
        photoId = id,
        title = title,
        albumId = albumId,
        userId = userId,
        thumbnailUrl = thumbnailUrl,
    )
}
