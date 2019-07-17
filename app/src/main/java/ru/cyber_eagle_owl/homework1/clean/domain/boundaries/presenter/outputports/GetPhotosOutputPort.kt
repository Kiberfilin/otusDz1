package ru.cyber_eagle_owl.homework1.clean.domain.boundaries.presenter.outputports

import ru.cyber_eagle_owl.homework1.clean.data.entities.presentation.PhotoPresentationEntity

interface GetPhotosOutputPort {

    fun onPhotosHasGotten(photos: ArrayList<PhotoPresentationEntity>)
}