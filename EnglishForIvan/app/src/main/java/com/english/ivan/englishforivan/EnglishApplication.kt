package com.english.ivan.englishforivan

import android.app.Application
import com.english.ivan.englishforivan.dagger.AppComponent
import com.english.ivan.englishforivan.dagger.DaggerAppComponent

class EnglishApplication: Application() {

    companion object {
        //platformStatic allow access it from java code
        @JvmStatic lateinit var graph: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        graph = DaggerAppComponent.builder().build()

    }
}