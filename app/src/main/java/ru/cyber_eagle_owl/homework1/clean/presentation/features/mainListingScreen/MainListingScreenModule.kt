package ru.cyber_eagle_owl.homework1.clean.presentation.features.mainListingScreen

import dagger.Binds
import dagger.Module
import ru.cyber_eagle_owl.homework1.clean.data.repositories.PhotosRepository
import ru.cyber_eagle_owl.homework1.di.scopes.ActivityScope

@Module
abstract class MainListingScreenModule {

    @Binds
    @ActivityScope
    abstract fun view(activity: MainListingScreenActivity): MainListingScreenMvp.View

    @Binds
    @ActivityScope
    abstract fun presenter(presenter: MainListingScreenPresenter): MainListingScreenMvp.Presenter

}