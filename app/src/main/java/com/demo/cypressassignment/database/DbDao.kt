package com.demo.cypressassignment.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.demo.cypressassignment.model.PhotoDetail

@Dao
interface DbDao {

    @Query("SELECT * FROM photo")
    fun getPhotos(): List<PhotoDetail>

    @Insert
    fun addPhotos(list: List<PhotoDetail>)
}