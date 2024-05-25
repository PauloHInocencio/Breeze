package br.com.noartcode.breeze.android.di

import android.content.Context
import androidx.room.Room
import br.com.noartcode.breeze.android.BuildConfig
import br.com.noartcode.breeze.android.data.local.PhotographerDao
import br.com.noartcode.breeze.android.data.local.PhotosDao
import br.com.noartcode.breeze.android.data.local.PhotosDatabase
import br.com.noartcode.breeze.android.data.remote.BasicAuthInterceptor
import br.com.noartcode.breeze.android.data.remote.ConnectionInterceptor
import br.com.noartcode.breeze.android.data.remote.PhotosRetrofitApi
import br.com.noartcode.breeze.android.data.repository.PhotosRepositoryImp
import br.com.noartcode.breeze.android.domain.PhotosRepository
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
    fun providesPhotosRepository(api: PhotosRetrofitApi, dao: PhotosDao) : PhotosRepository {
        return PhotosRepositoryImp(api = api, photosDao = dao)
    }

    @Provides
    @Singleton
    fun providesPhotosDatabase(@ApplicationContext appContext: Context) : PhotosDatabase =
        Room.databaseBuilder(
            context = appContext,
            klass = PhotosDatabase::class.java,
            name ="photos-database"
        ).build()


    @Provides
    @Singleton
    fun providesPhotosDao(database: PhotosDatabase) : PhotosDao = database.photosDao()

    @Provides
    @Singleton
    fun providesPhotographerDao(database: PhotosDatabase) : PhotographerDao{
        return database.photographerDao()
    }



}