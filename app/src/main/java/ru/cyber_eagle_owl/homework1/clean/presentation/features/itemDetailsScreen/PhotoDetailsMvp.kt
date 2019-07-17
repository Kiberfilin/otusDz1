package ru.cyber_eagle_owl.homework1.clean.presentation.features.itemDetailsScreen

import ru.cyber_eagle_owl.homework1.clean.data.entities.presentation.PhotoDetailsPresentationEntity
import ru.cyber_eagle_owl.homework1.clean.data.entities.presentation.PhotoPresentationEntity
import ru.cyber_eagle_owl.homework1.clean.presentation.mvpcore.MvpPresenter
import ru.cyber_eagle_owl.homework1.clean.presentation.mvpcore.MvpView

interface PhotoDetailsMvp {

    interface View : MvpView {

        fun showPhotoInfo(photo: PhotoDetailsPresentationEntity)
    }

    interface Presenter : MvpPresenter<View> {

        fun onPhotoIdReceived(photoId: Int)

        fun onActivityDestroyed()
    }

    interface Model {

    }
}