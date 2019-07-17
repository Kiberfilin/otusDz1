package ru.cyber_eagle_owl.homework1.clean.presentation.features.mainListingScreen

import ru.cyber_eagle_owl.homework1.clean.data.entities.presentation.PhotoPresentationEntity
import ru.cyber_eagle_owl.homework1.clean.data.repositories.PhotosRepository
import ru.cyber_eagle_owl.homework1.clean.domain.boundaries.presenter.inputports.GetPhotosInputPort
import ru.cyber_eagle_owl.homework1.clean.domain.boundaries.presenter.outputports.GetPhotosOutputPort
import ru.cyber_eagle_owl.homework1.clean.domain.interactors.GetPhotosInteractor
import ru.cyber_eagle_owl.homework1.clean.presentation.mvpcore.BasePresenter
import timber.log.Timber
import javax.inject.Inject

class MainListingScreenPresenter @Inject constructor() :
    BasePresenter<MainListingScreenMvp.View>(),
    MainListingScreenMvp.Presenter,
    GetPhotosOutputPort {

    private val photosRepo: PhotosRepository = PhotosRepository()

    private val getPhotos: GetPhotosInputPort = GetPhotosInteractor(this, photosRepo)

    override fun onViewCreated() {
        Timber.d("0=(====> onViewCreated")

        getPhotos.execute()
    }

    override fun onPhotosHasGotten(photos: ArrayList<PhotoPresentationEntity>) {
        Timber.d("0=(====> onPhotosHasGotten")
        view.showListOfPhotos(photos)
    }

    override fun onActivityDestroyed() {
        photosRepo.cancelJob()
    }
}