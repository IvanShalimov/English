package com.english.ivan.englishforivan.custom

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.Screen

class SupportAppScreenX:Screen() {
    fun getFragment(): Fragment? {
        return null
    }

    fun getActivityIntent(context: Context): Intent? {
        return null
    }
}