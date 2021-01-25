package com.vaskevicius.android.vikingrecipe.di.component

import android.app.Application
import com.vaskevicius.android.vikingrecipe.VikingRecipeApplication
import com.vaskevicius.android.vikingrecipe.di.builder.ActivityBuilder
import com.vaskevicius.android.vikingrecipe.di.module.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [(AndroidInjectionModule::class), (ApplicationModule::class), (ActivityBuilder::class)])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: VikingRecipeApplication)

}