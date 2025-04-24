package com.devtor.amphibians

import android.app.Application
import com.devtor.amphibians.data.AppContainer
import com.devtor.amphibians.data.DefaultAppContainer

class AmphibiansApplication : Application(){
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}