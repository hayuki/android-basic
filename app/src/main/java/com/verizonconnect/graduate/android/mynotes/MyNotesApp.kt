package com.verizonconnect.graduate.android.mynotes

import android.app.Application
import com.verizonconnect.graduate.android.mynotes.di.myNotesModules
import org.koin.android.ext.android.startKoin

class MyNotesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin(this, myNotesModules)
    }
}