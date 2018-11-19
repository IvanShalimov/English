package com.english.ivan.englishforivan.dagger

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class RouteModule {

    private val cicirone: Cicerone<Router> = Cicerone.create()

    @Provides
    @Singleton
    fun provideRouter(): Router = cicirone.router

    @Provides
    @Singleton
    fun provideNavigatorHolder(): NavigatorHolder = cicirone.navigatorHolder
}