package com.vaskevicius.android.vikingrecipe.ui.searchResult

import com.vaskevicius.android.vikingrecipe.ui.preview.PreviewFragment
import com.vaskevicius.android.vikingrecipe.ui.preview.PreviewFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class SearchResultFragmentProvider {
    @ContributesAndroidInjector(modules = [SearchResultFragmentModule::class])
    internal abstract fun provideSearchResultFragmentFactory(): SearchResultFragment
}