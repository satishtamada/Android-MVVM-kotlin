package com.example.android_mvvm_kotlin.di

import android.app.Application
import android.content.Context
import android.text.TextUtils
import com.example.android_mvvm_kotlin.BuildConfig
import com.example.android_mvvm_kotlin.data.DataRepository
import com.example.android_mvvm_kotlin.helpers.AppPref
import com.example.android_mvvm_kotlin.helpers.ConnectivityInterceptor
import com.example.android_mvvm_kotlin.helpers.MyApplication
import com.example.android_mvvm_kotlin.network.WebService
import com.example.android_mvvm_kotlin.utils.Constants
import com.example.android_mvvm_kotlin.utils.UnauthorizedInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.SSLSession

@Module(includes = [ViewModelModule::class])
class AppModule {
    /*@Provides
    @Singleton
    AppPrefHelper provideAppPrefHelper(AppPrefHelper appPrefHelper) {
        return appPrefHelper;
    }

    @Provides
    @Singleton
    AppDatabaseHelper provideAppDatabaseHelper(AppDatabaseHelper appDatabaseHelper) {
        return appDatabaseHelper;
    }


    @Provides
    @Singleton
    AppDatabase provideAppDatabase() {
        return new AppDatabase();
    }


    @Provides
    @Singleton
    fun provideAppDatabase(): AppDatabase {
        return AppDatabase()
    }
*/

    /*@Provides
    @Singleton
    fun provideDataRepository(appPref:AppPref) : DataRepository {
        return DataRepository(appPref);
    }*/

    @Provides
    @Singleton
    fun provideContext(application: MyApplication): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Provides
    @PreferenceInfo
    fun providePreferenceName(): String {
        return AppPref.PREF_NAME
    }

    @Provides
    @Singleton
    fun provideAppPrefs(context: Context): AppPref {
        return AppPref(context)
    }

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    internal fun provideOkHttp(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BuildConfig.BASE_ENDPOINT)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideApi(retrofit: Retrofit): WebService {
        return retrofit.create(WebService::class.java)
    }
/*
    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): WebService {
        return retrofit.create(WebService::class.java)
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient?): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor?, context: Context?, appPref: AppPref): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(Constants.CONNECTION_TIME_OUT.toLong(), TimeUnit.MINUTES)
            .readTimeout(Constants.getConnectionTimeOut(), TimeUnit.MINUTES)
            .writeTimeout(Constants.getConnectionTimeOut(), TimeUnit.MINUTES)
        builder.hostnameVerifier { hostname: String?, session: SSLSession? -> true }
        if (BuildConfig.DEBUG) { // Added interceptor for http logging
            builder.addInterceptor(httpLoggingInterceptor!!)
        }
        builder.addInterceptor(UnauthorizedInterceptor())
        builder.addInterceptor(ConnectivityInterceptor(context!!))
        builder.addInterceptor { chain: Interceptor.Chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader("Accept", "application/json")
            val userToken: String = appPref.authToken
            if (!TextUtils.isEmpty(userToken)) {
                requestBuilder.addHeader("Authorization", "Bearer $userToken")
            }
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        return builder.build()
    }*/
}