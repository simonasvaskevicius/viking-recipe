package com.vaskevicius.android.vikingrecipe.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Recipe(
    @Expose
    @SerializedName("idMeal")
    var id: Long? = null,

    @Expose
    @SerializedName("strMeal")
    var mealTitle: String? = null,

    @Expose
    @SerializedName("strCategory")
    var category: String? = null,

    @Expose
    @SerializedName("strTags")
    var tags: String? = null,

    @Expose
    @SerializedName("strArea")
    var area: String? = null,

    @Expose
    @SerializedName("strInstructions")
    var instructions: String? = null,

    @Expose
    @SerializedName("strMealThumb")
    var mealThumb: String? = null,

    @Expose
    @SerializedName("strYoutube")
    var youtubeLink: String? = null,

    @Expose
    @SerializedName("strSource")
    var sourceLink: String? = null,

    @Expose
    @SerializedName("strIngredient1")
    var strIngredient1: String?,

    @Expose
    @SerializedName("strIngredient2")
    var strIngredient2: String?,

    @Expose
    @SerializedName("strIngredient3")
    var strIngredient3: String?,

    @Expose
    @SerializedName("strIngredient4")
    var strIngredient4: String?,

    @Expose
    @SerializedName("strIngredient5")
    var strIngredient5: String?,

    @Expose
    @SerializedName("strIngredient6")
    var strIngredient6: String?,

    @Expose
    @SerializedName("strIngredient7")
    var strIngredient7: String?,

    @Expose
    @SerializedName("strIngredient8")
    var strIngredient8: String?,

    @Expose
    @SerializedName("strIngredient9")
    var strIngredient9: String?,

    @Expose
    @SerializedName("strIngredient10")
    var strIngredient10: String?,

    @Expose
    @SerializedName("strIngredient11")
    var strIngredient11: String?,

    @Expose
    @SerializedName("strIngredient12")
    var strIngredient12: String?,

    @Expose
    @SerializedName("strIngredient13")
    var strIngredient13: String?,

    @Expose
    @SerializedName("strIngredient14")
    var strIngredient14: String?,

    @Expose
    @SerializedName("strIngredient15")
    var strIngredient15: String?,

    @Expose
    @SerializedName("strIngredient16")
    var strIngredient16: String?,

    @Expose
    @SerializedName("strIngredient17")
    var strIngredient17: String?,

    @Expose
    @SerializedName("strIngredient18")
    var strIngredient18: String?,

    @Expose
    @SerializedName("strIngredient19")
    var strIngredient19: String?,

    @Expose
    @SerializedName("strIngredient20")
    var strIngredient20: String?,

    @Expose
    @SerializedName("strMeasure1")
    var strMeasure1: String?,

    @Expose
    @SerializedName("strMeasure2")
    var strMeasure2: String?,

    @Expose
    @SerializedName("strMeasure3")
    var strMeasure3: String?,

    @Expose
    @SerializedName("strMeasure4")
    var strMeasure4: String?,

    @Expose
    @SerializedName("strMeasure5")
    var strMeasure5: String?,

    @Expose
    @SerializedName("strMeasure6")
    var strMeasure6: String?,

    @Expose
    @SerializedName("strMeasure7")
    var strMeasure7: String?,

    @Expose
    @SerializedName("strMeasure8")
    var strMeasure8: String?,

    @Expose
    @SerializedName("strMeasure9")
    var strMeasure9: String?,

    @Expose
    @SerializedName("strMeasure10")
    var strMeasure10: String?,

    @Expose
    @SerializedName("strMeasure11")
    var strMeasure11: String?,

    @Expose
    @SerializedName("strMeasure12")
    var strMeasure12: String?,

    @Expose
    @SerializedName("strMeasure13")
    var strMeasure13: String?,

    @Expose
    @SerializedName("strMeasure14")
    var strMeasure14: String?,

    @Expose
    @SerializedName("strMeasure15")
    var strMeasure15: String?,

    @Expose
    @SerializedName("strMeasure16")
    var strMeasure16: String?,

    @Expose
    @SerializedName("strMeasure17")
    var strMeasure17: String?,

    @Expose
    @SerializedName("strMeasure18")
    var strMeasure18: String?,

    @Expose
    @SerializedName("strMeasure19")
    var strMeasure19: String?,

    @Expose
    @SerializedName("strMeasure20")
    var strMeasure20: String?
)