package com.english.ivan.englishforivan.dagger

import com.english.ivan.englishforivan.StartActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(RouteModule::class))
interface AppComponent {

    fun inject(activity:StartActivity)
}