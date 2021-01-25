package com.vaskevicius.android.vikingrecipe.ui.home

import com.vaskevicius.android.vikingrecipe.base.MVPInteractor
import com.vaskevicius.android.vikingrecipe.data.models.response.CategoryResponse
import com.vaskevicius.android.vikingrecipe.data.models.response.RecipeResponse
import com.vaskevicius.android.vikingrecipe.data.models.response.SearchResponse
import io.reactivex.Observable

interface HomeMVPInteractor : MVPInteractor {
    fun getRandomRecipe(): Observable<RecipeResponse>

    fun getCategoriesApiCall(): Observable<CategoryResponse>

    fun getSearchApiCall(keyword: String): Observable<SearchResponse>
}