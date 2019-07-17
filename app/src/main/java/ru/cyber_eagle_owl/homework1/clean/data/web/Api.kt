package ru.cyber_eagle_owl.homework1.clean.data.web

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import ru.cyber_eagle_owl.homework1.clean.data.entities.data.AlbumDataEntity
import ru.cyber_eagle_owl.homework1.clean.data.entities.data.PhotoDataEntity

interface Api {

    @GET("photos")
    suspend fun getPhotos() : Response<ArrayList<PhotoDataEntity>>

    @GET("photos/{photoId}")
    suspend fun getPhotoById(@Path("photoId") photoId: String): Response<PhotoDataEntity>

    @GET("albums/{albumId}")
    suspend fun getAlbumById(@Path("albumId") albumId: String): Response<AlbumDataEntity>
}