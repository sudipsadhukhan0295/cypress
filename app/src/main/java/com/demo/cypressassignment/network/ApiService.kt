package com.demo.cypressassignment.network

import com.demo.cypressassignment.model.AlbumDetailDto
import com.demo.cypressassignment.model.PhotoDetail
import com.demo.cypressassignment.model.PhotoDetailDto
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("photos")
    suspend fun getPhotos(@Query("albumId") request : Int):List<PhotoDetailDto>
    @GET("albums")
    suspend fun getAlbums():List<AlbumDetailDto>


    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        fun create(): ApiService {
            //val inspector = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                //.addInterceptor(inspector)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}