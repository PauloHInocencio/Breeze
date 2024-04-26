package br.com.noartcode.unsplashapp.android.di

import android.content.Context
import br.com.noartcode.unsplashapp.android.BuildConfig
import br.com.noartcode.unsplashapp.android.api.BasicAuthInterceptor
import br.com.noartcode.unsplashapp.android.api.ConnectionInterceptor
import br.com.noartcode.unsplashapp.android.api.PhotosRetrofitApi
import br.com.noartcode.unsplashapp.android.data.repository.PhotosRepositoryImp
import br.com.noartcode.unsplashapp.android.domain.PhotosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRetrofitApi(@ApplicationContext appContext: Context) : PhotosRetrofitApi {
        val logInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder().run {
            addInterceptor(logInterceptor)
            addInterceptor(ConnectionInterceptor(appContext.applicationContext))
            addInterceptor(BasicAuthInterceptor("Client-ID", BuildConfig.API_ACCESS_KEY))
            build()
        }

        return Retrofit.Builder().run {
            client(okHttpClient)
            baseUrl(PhotosRetrofitApi.BASE_URL)
            addConverterFactory(MoshiConverterFactory.create())
            build()
        }.create(PhotosRetrofitApi::class.java)
    }

    @Provides
    @Singleton
    fun providesPhotosRepository(api: PhotosRetrofitApi) : PhotosRepository {
        return PhotosRepositoryImp(api = api)
    }
}