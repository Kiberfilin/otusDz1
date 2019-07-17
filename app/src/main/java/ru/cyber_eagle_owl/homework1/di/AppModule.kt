package ru.cyber_eagle_owl.homework1.di

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerApplication
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.cyber_eagle_owl.homework1.clean.data.web.Api
import ru.cyber_eagle_owl.homework1.constants.AppConstants
import ru.cyber_eagle_owl.homework1.di.scopes.ApplicationScope

@Module
class AppModule(private val application: DaggerApplication) {

    @Provides
    @ApplicationScope
    fun provideApplicationContext(): Context = application.applicationContext
}