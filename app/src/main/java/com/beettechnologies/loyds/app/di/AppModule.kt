package com.beettechnologies.loyds.app.di

import android.content.Context
import com.beettechnologies.loyds.app.App
import com.beettechnologies.loyds.app.AppStorage
import com.beettechnologies.loyds.app.navigation.Navigation
import com.beettechnologies.loyds.app.navigation.NavigationImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext context: Context): App {
        return context as App
    }

    @Singleton
    @Provides
    fun provideAppStorage(@ApplicationContext context: Context): AppStorage {
        return AppStorage(context)
    }

    @Singleton
    @Provides
    fun provideNavigation(): Navigation {
        return NavigationImpl()
    }
}
