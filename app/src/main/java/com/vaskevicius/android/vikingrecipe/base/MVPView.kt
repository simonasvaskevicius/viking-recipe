package com.vaskevicius.android.vikingrecipe.base

interface MVPView {

    fun showProgress()

    fun hideProgress()

    fun handleNetworkError()

    fun handleNetworkLoss()

    fun handleNetworkReconnection()

}