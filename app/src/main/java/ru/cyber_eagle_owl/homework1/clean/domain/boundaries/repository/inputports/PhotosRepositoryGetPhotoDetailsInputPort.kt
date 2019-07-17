package ru.cyber_eagle_owl.homework1.clean.domain.boundaries.repository.inputports

import ru.cyber_eagle_owl.homework1.clean.domain.boundaries.repository.outputports.PhotosRepositoryGetPhotoDetailsOutputPort

interface PhotosRepositoryGetPhotoDetailsInputPort {

    fun setGetPhotoDetailsOutputPort(outputPort: PhotosRepositoryGetPhotoDetailsOutputPort)

    fun getPhotoDetails(photoId: Int)
}