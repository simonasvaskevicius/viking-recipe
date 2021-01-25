package com.vaskevicius.android.vikingrecipe.ui.preview

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class PreviewFragmentProvider {

    @ContributesAndroidInjector(modules = [PreviewFragmentModule::class])
    internal abstract fun providePreviewFragmentFactory(): PreviewFragment
}