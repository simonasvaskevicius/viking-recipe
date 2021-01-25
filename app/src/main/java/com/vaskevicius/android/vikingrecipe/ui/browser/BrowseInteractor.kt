package com.vaskevicius.android.vikingrecipe.ui.browser

import com.vaskevicius.android.vikingrecipe.base.BaseInteractor
import com.vaskevicius.android.vikingrecipe.data.models.response.RecipeResponse
import com.vaskevicius.android.vikingrecipe.data.network.ApiHelper
import com.vaskevicius.android.vikingrecipe.data.prefs.PreferenceHelper
import io.reactivex.Observable
import javax.inject.Inject

class BrowseInteractor @Inject internal constructor(preferenceHelper: PreferenceHelper, apiHelper: ApiHelper) : BaseInteractor(preferenceHelper, apiHelper), BrowseMVPInteractor {

    override fun getCategoryApiCall(category: String): Observable<RecipeResponse> = apiHelper.getFilterCategoryApiCall(category)

    override fun getFilterAreaApiCall(area: String): Observable<RecipeResponse> = apiHelper.getFilterAreaApiCall(area)

    override fun getRecipeById(id: Long): Observable<RecipeResponse> = apiHelper.getRecipeByIdApiCall(id)
}