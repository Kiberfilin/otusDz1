package ru.cyber_eagle_owl.homework1.clean.domain.boundaries.presenter.inputports

interface GetPhotoDetailsInputPort {

    fun execute(photoId: Int)
}