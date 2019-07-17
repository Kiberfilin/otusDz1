package ru.cyber_eagle_owl.homework1.clean.presentation.features.itemDetailsScreen

import ru.cyber_eagle_owl.homework1.clean.data.entities.presentation.PhotoDetailsPresentationEntity
import ru.cyber_eagle_owl.homework1.clean.data.repositories.PhotosRepository
import ru.cyber_eagle_owl.homework1.clean.domain.boundaries.presenter.inputports.GetPhotoDetailsInputPort
import ru.cyber_eagle_owl.homework1.clean.domain.boundaries.presenter.outputports.GetPhotoDetailsOutputPort
import ru.cyber_eagle_owl.homework1.clean.domain.interactors.GetPhotoDetailsInteractor
import ru.cyber_eagle_owl.homework1.clean.presentation.mvpcore.BasePresenter
import javax.inject.Inject

class PhotoDetailsPresenter @Inject constructor() :
    BasePresenter<PhotoDetailsMvp.View>(),
    PhotoDetailsMvp.Presenter,
    GetPhotoDetailsOutputPort {

    private val photosRepo: PhotosRepository = PhotosRepository()

    private val getPhotoDetails: GetPhotoDetailsInputPort = GetPhotoDetailsInteractor(this, photosRepo)

    override fun onPhotoIdReceived(photoId: Int) {

        getPhotoDetails.execute(photoId)
    }

    override fun onPhotoDetailsHasGotten(photoDetails: PhotoDetailsPresentationEntity) {

        view.showPhotoInfo(photoDetails)
    }

    override fun onActivityDestroyed() {
        photosRepo.cancelJob()
    }
}