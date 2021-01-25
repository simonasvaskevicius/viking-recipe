package com.vaskevicius.android.vikingrecipe.ui.browser

import dagger.Module
import dagger.Provides

@Module
class BrowseFragmentModule {

    @Provides
    internal fun provideBrowseInteractor(interactor: BrowseInteractor): BrowseMVPInteractor = interactor

    @Provides
    internal fun provideBrowsePresenter(presenter: BrowsePresenter<BrowseMVPView, BrowseMVPInteractor>)
            : BrowseMVPPresenter<BrowseMVPView, BrowseMVPInteractor> = presenter

    @Provides
    internal fun provideBrowseAdapter(): BrowseAdapter = BrowseAdapter(ArrayList())

}