package com.beettechnologies.loyds.app.di

import com.beettechnologies.loyds.app.navigation.Navigation
import com.beettechnologies.loyds.app.navigation.NavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindNavigation(navigationImpl: NavigationImpl): Navigation
}
