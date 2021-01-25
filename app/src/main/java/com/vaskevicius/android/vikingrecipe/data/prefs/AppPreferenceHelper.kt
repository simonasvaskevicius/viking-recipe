package com.vaskevicius.android.vikingrecipe.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.vaskevicius.android.vikingrecipe.di.PreferenceInfo

import javax.inject.Inject


class AppPreferenceHelper @Inject constructor(
    context: Context,
    @PreferenceInfo private val prefFileName: String
) : PreferenceHelper {
    companion object {
    }

    private val mPrefs: SharedPreferences =
        context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

}