package ru.cyber_eagle_owl.homework1.clean.domain.interactors

import ru.cyber_eagle_owl.homework1.clean.data.entities.presentation.PhotoPresentationEntity
import ru.cyber_eagle_owl.homework1.clean.domain.boundaries.presenter.inputports.GetPhotosInputPort
import ru.cyber_eagle_owl.homework1.clean.domain.boundaries.presenter.outputports.GetPhotosOutputPort
import ru.cyber_eagle_owl.homework1.clean.domain.boundaries.repository.inputports.PhotosRepositoryGetPhotosInputPort
import ru.cyber_eagle_owl.homework1.clean.domain.boundaries.repository.outputports.PhotosRepositoryGetPhotosOutputPort
import timber.log.Timber

class GetPhotosInteractor(
    private val presentationOutputPort: GetPhotosOutputPort,
    private val dataInputPort: PhotosRepositoryGetPhotosInputPort
) : GetPhotosInputPort, PhotosRepositoryGetPhotosOutputPort {

    init {
        dataInputPort.setGetPhotosOutputPort(this)
    }

    override fun execute() {
        Timber.d("0=(====> execute()")
        dataInputPort.getPhotos()
    }

    override fun onPhotosHasGotten(photos: ArrayList<PhotoPresentationEntity>) {
        Timber.d("0=(====> onPhotosHasGotten()")

        presentationOutputPort.onPhotosHasGotten(photos)
    }

}