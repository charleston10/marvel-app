package com.charleston.marvelapp.config

import android.app.Application
import android.content.Context

class AndroidTestRunner : androidx.test.runner.AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, KoinTestApplication::class.java.name, context)
    }
}