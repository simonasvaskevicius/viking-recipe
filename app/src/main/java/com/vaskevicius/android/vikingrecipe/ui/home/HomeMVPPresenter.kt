package com.vaskevicius.android.vikingrecipe.ui.home

import com.vaskevicius.android.vikingrecipe.base.MVPPresenter

interface HomeMVPPresenter<V : HomeMVPView, I : HomeMVPInteractor> : MVPPresenter<V, I> {

    fun onViewPrepared()

    fun search(keyword: String, listener: HomePresenter.OnFindSuggestListener)

    fun refresh()
}