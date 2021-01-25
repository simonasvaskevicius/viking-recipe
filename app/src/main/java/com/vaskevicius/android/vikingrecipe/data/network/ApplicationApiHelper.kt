package com.vaskevicius.android.vikingrecipe.data.network

import com.rx2androidnetworking.Rx2AndroidNetworking
import com.vaskevicius.android.vikingrecipe.data.models.response.CategoryResponse
import com.vaskevicius.android.vikingrecipe.data.models.response.RecipeResponse
import com.vaskevicius.android.vikingrecipe.data.models.response.SearchResponse
import io.reactivex.Observable
import javax.inject.Inject

class ApplicationApiHelper @Inject constructor() : ApiHelper {

    override fun getRandomRecipeApiCall(): Observable<RecipeResponse> =
        Rx2AndroidNetworking.get(ApiEndpoint.ENDPOINT_RANDOM_RECIPE).build()
            .getObjectObservable(RecipeResponse::class.java)

    override fun getCategoriesApiCall(): Observable<CategoryResponse> =
        Rx2AndroidNetworking.get(ApiEndpoint.ENDPOINT_CATEGORIES).build()
            .getObjectObservable(CategoryResponse::class.java)

    override fun getSearchApiCall(keyword: String): Observable<SearchResponse> =
        Rx2AndroidNetworking.get(ApiEndpoint.ENDPOINT_SEARCH + keyword).build()
            .getObjectObservable(SearchResponse::class.java)

    override fun getFilterCategoryApiCall(category: String): Observable<RecipeResponse> =
        Rx2AndroidNetworking.get(ApiEndpoint.ENDPOINT_FILTER_CATEGORY + category).build()
            .getObjectObservable(RecipeResponse::class.java)

    override fun getFilterAreaApiCall(area: String): Observable<RecipeResponse> =
        Rx2AndroidNetworking.get(ApiEndpoint.ENDPOINT_FILTER_AREA + area).build()
            .getObjectObservable(RecipeResponse::class.java)

    override fun getRecipeByIdApiCall(id: Long): Observable<RecipeResponse> =
        Rx2AndroidNetworking.get(ApiEndpoint.ENDPOINT_RECIPE_BY_ID + id).build()
            .getObjectObservable(RecipeResponse::class.java)
}