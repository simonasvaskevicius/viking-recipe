package com.vaskevicius.android.vikingrecipe.base

import android.app.ProgressDialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.pd.chocobar.ChocoBar
import com.vaskevicius.android.vikingrecipe.R
import com.vaskevicius.android.vikingrecipe.utils.CommonUtil
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.progress_dialog.*


abstract class BaseActivity : AppCompatActivity(), MVPView, BaseFragment.CallBack {

    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        performDI()
        super.onCreate(savedInstanceState)

        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager?.let {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                it.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        handleNetworkReconnection()
                    }

                    override fun onLost(network: Network) {
                        handleNetworkLoss()
                    }
                })
            }
        }
    }

    fun showNetworkErrorSnackbar() {
        showCustomSnackbar(resources.getString(R.string.httpError), resources.getDrawable(R.drawable.ic_baseline_error_outline_24))
    }

    fun showNetworkLossSnackbar() {
        showCustomSnackbar(resources.getString(R.string.networkErrorConnect), resources.getDrawable(R.drawable.ic_baseline_cloud_off_24))
    }

    fun showNetworkReconnectedSnackbar() {
        showCustomSnackbar(resources.getString(R.string.connected), resources.getDrawable(R.drawable.ic_baseline_cloud_done_24))
    }

    private fun showCustomSnackbar(text: String, drawable: Drawable) {
        ChocoBar.builder().setBackgroundColor(resources.getColor(R.color.black_effective))
            .setTextColor(resources.getColor(R.color.white)).setText(text).setIcon(drawable).centerText()
            .setDuration(2000).setActivity(this).build().show()
    }

    override fun hideProgress() {
        progressDialog?.let { if (it.isShowing) it.cancel() }
    }

    override fun showProgress() {
        hideProgress()
        progressDialog = CommonUtil.showLoadingDialog(this)
    }


    //if fragment is attached -> super.onBackPressed()
    //if not press twice to exit
    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        var count: Int = supportFragmentManager.backStackEntryCount;
        if (count == 0) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed()
                return
            }
            this.doubleBackToExitPressedOnce = true
            var icLogout = resources.getDrawable(R.drawable.ic_logout)
            icLogout.setTint(getColor(R.color.white))
            showCustomSnackbar(getString(R.string.exit_message), icLogout)
            Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    private fun performDI() = AndroidInjection.inject(this)

}