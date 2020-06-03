package com.charleston.marvelapp

import android.app.Application
import android.content.Context
import com.charleston.marvelapp.config.KoinTestApplication

class AndroidTestRunner : androidx.test.runner.AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, KoinTestApplication::class.java.name, context)
    }
}