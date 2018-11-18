package com.english.ivan.englishforivan

import android.app.Application
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class EnglishApplication: Application() {

    init {
        initCicirone()
    }

    private fun initCicirone(){
        cicirone = Cicerone.create()
    }

    companion object {
        private lateinit var cicirone: Cicerone<Router>

        fun getNavigatorHolder() = cicirone.navigatorHolder

        fun getRouter() = cicirone.router
    }
}