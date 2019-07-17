package ru.cyber_eagle_owl.homework1.clean.data.repositories

import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.cyber_eagle_owl.homework1.clean.data.entities.data.AlbumDataEntity
import ru.cyber_eagle_owl.homework1.clean.data.entities.data.PhotoDataEntity
import ru.cyber_eagle_owl.homework1.clean.data.entities.presentation.PhotoDetailsPresentationEntity
import ru.cyber_eagle_owl.homework1.clean.data.entities.presentation.PhotoPresentationEntity
import ru.cyber_eagle_owl.homework1.clean.data.web.Api
import ru.cyber_eagle_owl.homework1.clean.domain.boundaries.repository.inputports.PhotosRepositoryGetPhotoDetailsInputPort
import ru.cyber_eagle_owl.homework1.clean.domain.boundaries.repository.inputports.PhotosRepositoryGetPhotosInputPort
import ru.cyber_eagle_owl.homework1.clean.domain.boundaries.repository.outputports.PhotosRepositoryGetPhotoDetailsOutputPort
import ru.cyber_eagle_owl.homework1.clean.domain.boundaries.repository.outputports.PhotosRepositoryGetPhotosOutputPort
import ru.cyber_eagle_owl.homework1.constants.AppConstants
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class PhotosRepository :
    PhotosRepositoryGetPhotosInputPort,
    PhotosRepositoryGetPhotoDetailsInputPort,
    CoroutineScope {

    private lateinit var getPhotosInteractor: PhotosRepositoryGetPhotosOutputPort
    private lateinit var getPhotoDetailsInteractor: PhotosRepositoryGetPhotoDetailsOutputPort

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private var apiService: Api = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(AppConstants.baseUrlForRetrofit)
        .build().create(Api::class.java)

    override fun setGetPhotosOutputPort(outputPort: PhotosRepositoryGetPhotosOutputPort) {
        Timber.d("0=(====> setGetPhotosOutputPort")
        getPhotosInteractor = outputPort
    }

    override fun setGetPhotoDetailsOutputPort(outputPort: PhotosRepositoryGetPhotoDetailsOutputPort) {
        Timber.d("0=(====> setGetPhotoDetailsOutputPort")
        getPhotoDetailsInteractor = outputPort
    }

    override fun getPhotos() {
        Timber.d("0=(====> getPhotos")

        CoroutineScope(Dispatchers.IO).launch {
            Timber.d("0=(====> inside CoroutineScope(Dispatchers.IO).launch")
            val response = apiService.getPhotos()
            withContext(Dispatchers.Main) {
                Timber.d("0=(====> inside withContext(Dispatchers.Main)")
                try {
                    if (response.isSuccessful) {
                        //Do something with response e.g show to the UI.
                        response.body()?.let { listOfPhotosData ->
                            getPhotosInteractor.onPhotosHasGotten(mapListOfPhotosFromDataToPresentation(listOfPhotosData))
                        }
                    } else {
                        Timber.d("0=(====> Error: ${response.code()}")
                    }
                } catch (e: HttpException) {
                    Timber.d("0=(====> Exception ${e.message}")
                } catch (e: Throwable) {
                    Timber.d("0=(====> Ooops: Something else went wrong")
                }
            }
        }
    }

    override fun getPhotoDetails(photoId: Int) {
        Timber.d("getPhotoDetails(photoId: Int)")

        var tmpPhotoDetails = PhotoDetailsPresentationEntity(-1, "", "", "")

        CoroutineScope(Dispatchers.IO).launch {
            val response = apiService.getPhotoById(photoId.toString())
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        //Do something with response e.g show to the UI.
                        response.body()?.let { photoData ->
                            getPhotoDetailsInteractor.onPhotoDetailsHasGotten(
                                mapPhotoDataDetailsToPresentation(photoData, getAlbum(photoData.albumId))
                            )
                        }
                    } else {
                        Timber.d("0=(====> Error: ${response.code()}")
                    }
                } catch (e: HttpException) {
                    Timber.d("0=(====> Exception ${e.message}")
                } catch (e: Throwable) {
                    Timber.d("0=(====> Ooops: Something else went wrong")
                }
            }
        }
    }

    suspend fun getAlbum(albumId: Int): AlbumDataEntity {
        var tmpAlbumData = AlbumDataEntity(-1, -1, "")
        Timber.d("getAlbum(albumId: Int): AlbumDataEntity")
        val response = apiService.getAlbumById(albumId.toString())
        try {
            if (response.isSuccessful) {
                //Do something with response e.g show to the UI.
                response.body()?.let { albumData ->
                    Timber.d(albumData.toString())
                    tmpAlbumData = albumData
                }
            } else {
                Timber.d("0=(====> Error: ${response.code()}")
            }
        } catch (e: HttpException) {
            Timber.d("0=(====> Exception ${e.message}")
        } catch (e: Throwable) {
            Timber.d("0=(====> Ooops: Something else went wrong")
        }
        return tmpAlbumData
    }

    private fun mapPhotoFromDataToPresentation(photoDataEntity: PhotoDataEntity): PhotoPresentationEntity {
        Timber.d("0=(====> mapPhotoFromDataToPresentation")

        return PhotoPresentationEntity(
            photoDataEntity.id,
            photoDataEntity.title,
            photoDataEntity.thumbnailUrl
        )
    }

    private fun mapListOfPhotosFromDataToPresentation(listOfPhotoDataEntityes: ArrayList<PhotoDataEntity>): ArrayList<PhotoPresentationEntity> {
        Timber.d("0=(====> mapListOfPhotosFromDataToPresentation")

        val listOfPhotoPresentationEntityes = ArrayList<PhotoPresentationEntity>()
        listOfPhotoDataEntityes.forEach {
            listOfPhotoPresentationEntityes.add(mapPhotoFromDataToPresentation(it))
        }

        return listOfPhotoPresentationEntityes
    }

    private fun mapPhotoDataDetailsToPresentation(
        photo: PhotoDataEntity,
        album: AlbumDataEntity
    ): PhotoDetailsPresentationEntity {
        return PhotoDetailsPresentationEntity(
            photo.id,
            photo.title,
            album.title,
            photo.url
        )
    }

    fun cancelJob() {

        if (this::job.isInitialized) job.cancel()
    }
}