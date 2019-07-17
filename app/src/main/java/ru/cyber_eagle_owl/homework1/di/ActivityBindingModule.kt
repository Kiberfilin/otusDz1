package ru.cyber_eagle_owl.homework1.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.cyber_eagle_owl.homework1.clean.presentation.features.itemDetailsScreen.PhotoDetailsActivity
import ru.cyber_eagle_owl.homework1.clean.presentation.features.itemDetailsScreen.PhotoDetailsModule
import ru.cyber_eagle_owl.homework1.clean.presentation.features.mainListingScreen.MainListingScreenActivity
import ru.cyber_eagle_owl.homework1.clean.presentation.features.mainListingScreen.MainListingScreenModule
import ru.cyber_eagle_owl.homework1.di.scopes.ActivityScope

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainListingScreenModule::class])
    abstract fun bindMainActivity(): MainListingScreenActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [PhotoDetailsModule::class])
    abstract fun bindPhotoDetailsActivity(): PhotoDetailsActivity
}