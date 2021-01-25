package com.vaskevicius.android.vikingrecipe.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Category (
    @Expose
    @SerializedName("idCategory")
    var id: Long? = null,

    @Expose
    @SerializedName("strCategory")
    var title: String? = null,

    @Expose
    @SerializedName("strCategoryThumb")
    var categoryThumb: String? = null,

    @Expose
    @SerializedName("strCategoryDescription")
    var description: String? = null)