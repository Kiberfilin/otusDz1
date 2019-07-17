package ru.cyber_eagle_owl.homework1.clean.domain.boundaries.repository.outputports

import ru.cyber_eagle_owl.homework1.clean.data.entities.presentation.PhotoPresentationEntity

interface PhotosRepositoryGetPhotosOutputPort {

    fun onPhotosHasGotten(photos : ArrayList<PhotoPresentationEntity>)

}