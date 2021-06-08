package com.mnhyim.moviecatalog

import android.app.Application
import com.mnhyim.moviecatalog.di.databaseModule
import com.mnhyim.moviecatalog.di.networkModule
import com.mnhyim.moviecatalog.di.repositoryModule
import com.mnhyim.moviecatalog.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(org.koin.core.logger.Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}