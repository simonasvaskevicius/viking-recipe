package com.vaskevicius.android.vikingrecipe.data.models.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vaskevicius.android.vikingrecipe.data.models.Recipe

data class RecipeResponse(
    @Expose
    @SerializedName("meals")
    var data: List<Recipe>? = null)