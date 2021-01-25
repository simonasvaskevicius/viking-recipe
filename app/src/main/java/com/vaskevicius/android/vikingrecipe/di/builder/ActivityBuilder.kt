package com.vaskevicius.android.vikingrecipe.di.builder

import com.vaskevicius.android.vikingrecipe.ui.browser.BrowseFragmentProvider
import com.vaskevicius.android.vikingrecipe.ui.home.HomeActivity
import com.vaskevicius.android.vikingrecipe.ui.home.HomeActivityModule
import com.vaskevicius.android.vikingrecipe.ui.preview.PreviewFragmentProvider
import com.vaskevicius.android.vikingrecipe.ui.searchResult.SearchResultFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(HomeActivityModule::class), (PreviewFragmentProvider::class), (BrowseFragmentProvider::class), (SearchResultFragmentProvider::class)])
    abstract fun bindHomeActivity(): HomeActivity

}