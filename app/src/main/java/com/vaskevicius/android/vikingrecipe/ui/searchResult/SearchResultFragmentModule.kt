package com.vaskevicius.android.vikingrecipe.ui.searchResult

import com.vaskevicius.android.vikingrecipe.ui.browser.BrowseAdapter
import com.vaskevicius.android.vikingrecipe.ui.home.HomeMVPInteractor
import com.vaskevicius.android.vikingrecipe.ui.home.HomeMVPPresenter
import com.vaskevicius.android.vikingrecipe.ui.home.HomeMVPView
import com.vaskevicius.android.vikingrecipe.ui.home.HomePresenter
import dagger.Module
import dagger.Provides

@Module
class SearchResultFragmentModule {

    @Provides
    internal fun provideSearchResultPresenter(searchResultPresenter: SearchResultPresenter<SearchResultMVPView, HomeMVPInteractor>)
            : SearchResultMVPPresenter<SearchResultMVPView, HomeMVPInteractor> = searchResultPresenter

    @Provides
    internal fun provideBrowseAdapter(): BrowseAdapter = BrowseAdapter(ArrayList())

}