package com.mnhyim.moviecatalog.di

import androidx.room.Room
import com.mnhyim.moviecatalog.data.CatalogRepository
import com.mnhyim.moviecatalog.data.local.LocalDataSource
import com.mnhyim.moviecatalog.data.local.room.CatalogRoomDatabase
import com.mnhyim.moviecatalog.data.remote.RemoteDataSource
import com.mnhyim.moviecatalog.data.remote.api.ApiService
import com.mnhyim.moviecatalog.ui.viewmodels.DetailViewModel
import com.mnhyim.moviecatalog.ui.viewmodels.MoviesViewModel
import com.mnhyim.moviecatalog.ui.viewmodels.ShowsViewModel
import com.mnhyim.moviecatalog.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<CatalogRoomDatabase>().movieDao() }
    factory { get<CatalogRoomDatabase>().showDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            CatalogRoomDatabase::class.java, "catalog.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get(), get()) }
    single { RemoteDataSource(get()) }
    single { CatalogRepository(get(), get()) }
}

val viewModelModule = module {
    viewModel { MoviesViewModel(get()) }
    viewModel { ShowsViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}