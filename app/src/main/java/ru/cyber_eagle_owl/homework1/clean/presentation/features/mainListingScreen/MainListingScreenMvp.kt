package ru.cyber_eagle_owl.homework1.clean.presentation.features.mainListingScreen

import ru.cyber_eagle_owl.homework1.clean.data.entities.presentation.PhotoPresentationEntity
import ru.cyber_eagle_owl.homework1.clean.presentation.mvpcore.MvpPresenter
import ru.cyber_eagle_owl.homework1.clean.presentation.mvpcore.MvpView

interface MainListingScreenMvp {

    interface View : MvpView {

        fun showListOfPhotos(photos: ArrayList<PhotoPresentationEntity>)
    }

    interface Presenter : MvpPresenter<View> {

        fun onViewCreated()

        fun onActivityDestroyed()
    }

    interface Model {

    }
}