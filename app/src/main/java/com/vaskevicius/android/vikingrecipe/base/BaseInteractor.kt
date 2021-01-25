package com.vaskevicius.android.vikingrecipe.base

import com.vaskevicius.android.vikingrecipe.data.network.ApiHelper
import com.vaskevicius.android.vikingrecipe.data.prefs.PreferenceHelper

open class BaseInteractor() : MVPInteractor {

    protected lateinit var preferenceHelper: PreferenceHelper
    protected lateinit var apiHelper: ApiHelper

    constructor(prefs: PreferenceHelper, apiHelper: ApiHelper) : this() {
        this.preferenceHelper = prefs
        this.apiHelper = apiHelper
    }

}