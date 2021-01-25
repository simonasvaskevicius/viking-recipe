package com.vaskevicius.android.vikingrecipe.data.network

import com.vaskevicius.android.vikingrecipe.data.models.response.CategoryResponse
import com.vaskevicius.android.vikingrecipe.data.models.response.RecipeResponse
import com.vaskevicius.android.vikingrecipe.data.models.response.SearchResponse
import io.reactivex.Observable

interface ApiHelper {

    fun getRandomRecipeApiCall(): Observable<RecipeResponse>

    fun getCategoriesApiCall(): Observable<CategoryResponse>

    fun getSearchApiCall(keyword: String): Observable<SearchResponse>

    fun getFilterCategoryApiCall(category: String): Observable<RecipeResponse>

    fun getFilterAreaApiCall(area: String): Observable<RecipeResponse>

    fun getRecipeByIdApiCall(id: Long): Observable<RecipeResponse>
}