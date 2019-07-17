package ru.cyber_eagle_owl.homework1.clean.domain.boundaries.repository.inputports

import ru.cyber_eagle_owl.homework1.clean.domain.boundaries.repository.outputports.PhotosRepositoryGetPhotosOutputPort

interface PhotosRepositoryGetPhotosInputPort {

    fun setGetPhotosOutputPort(outputPort: PhotosRepositoryGetPhotosOutputPort)

    fun getPhotos()
}