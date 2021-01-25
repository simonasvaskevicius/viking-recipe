package com.vaskevicius.android.vikingrecipe.ui.searchResult

import com.vaskevicius.android.vikingrecipe.base.MVPPresenter
import com.vaskevicius.android.vikingrecipe.ui.home.HomeMVPInteractor

interface SearchResultMVPPresenter<V : SearchResultMVPView, I : HomeMVPInteractor> : MVPPresenter<V, I> {

    fun search(keyword: String, listener: SearchResultPresenter.OnFindSuggestListener)
}