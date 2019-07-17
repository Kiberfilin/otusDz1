package ru.cyber_eagle_owl.homework1.clean.presentation.mvpcore

interface MvpPresenter<V : MvpView> {
    val view: V
}