package com.demo.cypressassignment.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "photo")
data class PhotoDetail(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var photoId: Int? = 0,
    var title: String? = "",
    var albumId: Int? = 0,
    var albumTitle: String? = "",
    var userId: Int? = 0,
    var thumbnailUrl: String? = ""
)