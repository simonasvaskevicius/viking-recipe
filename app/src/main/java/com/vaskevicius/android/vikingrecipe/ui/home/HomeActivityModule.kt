package com.vaskevicius.android.vikingrecipe.ui.home

import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import java.util.*

@Module
class HomeActivityModule {

    @Provides
    internal fun provideHomeInteractor(interactor: HomeInteractor): HomeMVPInteractor = interactor

    @Provides
    internal fun provideHomePresenter(homePresenter: HomePresenter<HomeMVPView, HomeMVPInteractor>)
            : HomeMVPPresenter<HomeMVPView, HomeMVPInteractor> = homePresenter

    @Provides
    internal fun provideHomeAdapter(): HomeAdapter = HomeAdapter(ArrayList())

    @Provides
    internal fun provideSearchSuggestAdapter(): SearchSuggestAdapter =
        SearchSuggestAdapter(ArrayList())


    @Provides
    internal fun provideLinearLayoutManager(activity: HomeActivity): LinearLayoutManager =
        LinearLayoutManager(activity)
}