package com.vaskevicius.android.vikingrecipe.ui.home

import com.vaskevicius.android.vikingrecipe.base.BaseInteractor
import com.vaskevicius.android.vikingrecipe.data.models.response.CategoryResponse
import com.vaskevicius.android.vikingrecipe.data.models.response.RecipeResponse
import com.vaskevicius.android.vikingrecipe.data.models.response.SearchResponse
import com.vaskevicius.android.vikingrecipe.data.network.ApiHelper
import com.vaskevicius.android.vikingrecipe.data.prefs.PreferenceHelper
import io.reactivex.Observable
import javax.inject.Inject

class HomeInteractor @Inject internal constructor(
    preferenceHelper: PreferenceHelper,
    apiHelper: ApiHelper
) : BaseInteractor(preferenceHelper, apiHelper), HomeMVPInteractor {

    override fun getRandomRecipe(): Observable<RecipeResponse> =
        apiHelper.getRandomRecipeApiCall()

    override fun getCategoriesApiCall(): Observable<CategoryResponse> =
        apiHelper.getCategoriesApiCall()

    override fun getSearchApiCall(keyword: String): Observable<SearchResponse> =
        apiHelper.getSearchApiCall(keyword)


}