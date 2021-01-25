package com.vaskevicius.android.vikingrecipe.di.module

import android.app.Application
import android.content.Context
import com.vaskevicius.android.vikingrecipe.BuildConfig
import com.vaskevicius.android.vikingrecipe.data.network.ApiHelper
import com.vaskevicius.android.vikingrecipe.data.network.ApplicationApiHelper
import com.vaskevicius.android.vikingrecipe.data.prefs.AppPreferenceHelper
import com.vaskevicius.android.vikingrecipe.data.prefs.PreferenceHelper
import com.vaskevicius.android.vikingrecipe.di.PreferenceInfo
import com.vaskevicius.android.vikingrecipe.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @PreferenceInfo
    internal fun providePrefFileName(): String = BuildConfig.PREF_NAME

    @Provides
    @Singleton
    internal fun providePrefHelper(appPreferenceHelper: AppPreferenceHelper): PreferenceHelper =
        appPreferenceHelper


    @Provides
    @Singleton
    internal fun provideApiHelper(appApiHelper: ApplicationApiHelper): ApiHelper = appApiHelper


    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()
}
