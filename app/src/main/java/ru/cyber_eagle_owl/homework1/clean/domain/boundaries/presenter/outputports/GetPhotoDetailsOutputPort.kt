package ru.cyber_eagle_owl.homework1.clean.domain.boundaries.presenter.outputports

import ru.cyber_eagle_owl.homework1.clean.data.entities.presentation.PhotoDetailsPresentationEntity

interface GetPhotoDetailsOutputPort {

    fun onPhotoDetailsHasGotten(photoDetails: PhotoDetailsPresentationEntity)
}