package com.vaskevicius.android.vikingrecipe.ui.browser

import com.vaskevicius.android.vikingrecipe.base.BasePresenter
import com.vaskevicius.android.vikingrecipe.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class BrowsePresenter<V : BrowseMVPView, I : BrowseMVPInteractor> @Inject constructor(
    interactor: I,
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable
) : BasePresenter<V, I>(
    interactor = interactor,
    schedulerProvider = schedulerProvider,
    compositeDisposable = compositeDisposable
), BrowseMVPPresenter<V, I> {

    var key: String? = null
    var methodId: Long? = null

    //if methodId is ID_CATEGORY_STRING or normal recipe id, presenter loads search results by category for "key"
    //if methodId is ID_AREA_STRING presenter loads search results for area by "key"
    override fun onViewPrepared(key: String, methodId: Long) {
        this.key = key
        this.methodId = methodId
        when (methodId) {
            BrowseFragment.ID_CATEGORY_STRING -> loadFilterCategory(key)
            BrowseFragment.ID_AREA_STRING -> loadFilterArea(key)
            else -> loadFilterCategory(key)
        }
    }

    private fun loadFilterCategory(category: String) {
        getView()?.showProgress()
        interactor?.let { it ->
            it.getCategoryApiCall(category).compose(schedulerProvider.ioToMainObservableScheduler())
                .subscribe({ categoryResponse ->
                    getView()?.let {
                        it.hideProgress()
                        it.displayRecipeList(categoryResponse.data!!)
                    }
                }, {
                    getView()?.handleNetworkError()
                })
        }
    }

    private fun loadFilterArea(area: String) {
        getView()?.showProgress()
        interactor?.let {
            it.getFilterAreaApiCall(area).compose(schedulerProvider.ioToMainObservableScheduler())
                .subscribe({ areaResponse ->
                    getView()?.let {
                        it.hideProgress()
                        it.displayRecipeList(areaResponse.data!!)
                    }
                }, {
                    getView()?.handleNetworkError()
                })
        }
    }

    override fun getRecipeById(id: Long) {
        getView()?.showProgress()
        interactor?.let {
            it.getRecipeById(id).compose(schedulerProvider.ioToMainObservableScheduler())
                .subscribe({ recipeResponse ->
                    getView()?.let {
                        it.hideProgress()
                        it.onRecipeByIdLoaded(recipeResponse.data?.get(0))
                    }
                }, {
                    getView()?.handleNetworkError()
                })
        }
    }

    override fun search(keyword: String) {
        TODO("Not yet implemented")
    }

    override fun refresh() {
        if (key != null && methodId != null) onViewPrepared(key!!, methodId!!)
    }


}