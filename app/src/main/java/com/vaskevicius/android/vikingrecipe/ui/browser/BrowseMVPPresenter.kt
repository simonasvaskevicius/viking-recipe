package com.vaskevicius.android.vikingrecipe.ui.browser

import com.vaskevicius.android.vikingrecipe.base.MVPPresenter

interface BrowseMVPPresenter<V : BrowseMVPView, I : BrowseMVPInteractor> : MVPPresenter<V, I> {

    fun onViewPrepared(key: String, methodId: Long)

    fun getRecipeById(id: Long)

    fun search(keyword: String)

    fun refresh()
}