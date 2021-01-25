package com.vaskevicius.android.vikingrecipe.ui.browser

import com.vaskevicius.android.vikingrecipe.base.MVPInteractor
import com.vaskevicius.android.vikingrecipe.data.models.response.RecipeResponse
import io.reactivex.Observable

interface BrowseMVPInteractor : MVPInteractor {

    fun getCategoryApiCall(category: String): Observable<RecipeResponse>

    fun getFilterAreaApiCall(area: String): Observable<RecipeResponse>

    fun getRecipeById(id: Long): Observable<RecipeResponse>
}