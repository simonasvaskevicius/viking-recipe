package com.vaskevicius.android.vikingrecipe.data.network

import com.vaskevicius.android.vikingrecipe.BuildConfig

object ApiEndpoint {
    const val ENDPOINT_RANDOM_RECIPE = BuildConfig.BASE_URL + "v1/1/random.php"
    const val ENDPOINT_CATEGORIES = BuildConfig.BASE_URL + "v1/1/categories.php"
    const val ENDPOINT_SEARCH = BuildConfig.BASE_URL + "v1/1/search.php?s="
    const val ENDPOINT_FILTER_CATEGORY = BuildConfig.BASE_URL + "v1/1/filter.php?c="
    const val ENDPOINT_FILTER_AREA = BuildConfig.BASE_URL + "v1/1/filter.php?a="
    const val ENDPOINT_RECIPE_BY_ID = BuildConfig.BASE_URL + "v1/1/lookup.php?i="

}