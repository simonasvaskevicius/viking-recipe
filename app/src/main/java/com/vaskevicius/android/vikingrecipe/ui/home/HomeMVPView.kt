package com.vaskevicius.android.vikingrecipe.ui.home

import com.vaskevicius.android.vikingrecipe.base.MVPView
import com.vaskevicius.android.vikingrecipe.data.models.Category
import com.vaskevicius.android.vikingrecipe.data.models.Recipe

interface HomeMVPView : MVPView {

    fun showSuggestProgress(show: Boolean)

    fun showSearchNoResults()

    fun displayRandomRecipe(recipe: Recipe?): Unit?

    fun displayCategoriesList(categories: List<Category>): Unit?

    fun openBrowseFragment(category: String, methodId: Long)

    fun openPreviewFragment(recipe: Recipe?)
}