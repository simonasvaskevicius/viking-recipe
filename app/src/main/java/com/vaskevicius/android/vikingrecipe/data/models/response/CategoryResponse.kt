package com.vaskevicius.android.vikingrecipe.data.models.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vaskevicius.android.vikingrecipe.data.models.Category

data class CategoryResponse(
        @Expose
        @SerializedName("categories")
        var data: List<Category>? = null)