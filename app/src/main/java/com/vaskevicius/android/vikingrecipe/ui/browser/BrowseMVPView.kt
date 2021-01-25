package com.vaskevicius.android.vikingrecipe.ui.browser

import com.vaskevicius.android.vikingrecipe.base.MVPView
import com.vaskevicius.android.vikingrecipe.data.models.Recipe

interface BrowseMVPView : MVPView {

    fun displayRecipeList(recipes: List<Recipe>): Unit?

    fun onRecipeByIdLoaded(recipe: Recipe?): Unit?
}