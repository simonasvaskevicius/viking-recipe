package com.vaskevicius.android.vikingrecipe.ui.browser

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class BrowseFragmentProvider {

    @ContributesAndroidInjector(modules = [(BrowseFragmentModule::class)])
    internal abstract fun provideBrowseFragmentFactory(): BrowseFragment

}