package com.example.groomexprez.common

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import com.example.groomexprez.AlertDialogFragment
import com.example.groomexprez.ProgressDialogFragment
import com.example.groomexprez.R

abstract class BaseActivity : AppCompatActivity(){

    private var progress: ProgressDialogFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        if (!BuildConfig.DEBUG)
//            window.setFlags(
//                WindowManager.LayoutParams.FLAG_SECURE,
//                WindowManager.LayoutParams.FLAG_SECURE
//            )
//        setContentView(getLayout())
        setupFocusOutside(findViewById(android.R.id.content))
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupFocusOutside(view: View) {
        if (view !is EditText) {
            view.setOnTouchListener { _, _ ->
                hideKeyboard()
                false
            }
        }

        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                setupFocusOutside(innerView)
            }
        }
    }

    private object ProgressDismissListener :
        ProgressDialogFragment.OnDismissListener {
        override fun onDismiss(dialog: DialogInterface?) {
            dialog?.dismiss()
        }
    }

    fun hideKeyboard() {
        val currentView = this.currentFocus
        if (currentView != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentView.windowToken, 0)
        }
    }

    @JvmOverloads
    open fun showProgress(
        message: String = "Processing...", cancellable: Boolean = false,
        dismissListener: ProgressDialogFragment.OnDismissListener = ProgressDismissListener
    ) {
        if (progress != null && progress?.isVisible!!) return

        progress = ProgressDialogFragment.Builder(
            dismissListener
        )
            .setTitle(message)
            .setCancelable(cancellable)
            .build()

        progress?.show(supportFragmentManager, "progress-dialog")
    }

    open fun hideProgress() {
        progress?.dismiss()
    }

    @JvmOverloads
    open fun showMessage(
        title: String,
        description: String,
        clickListener: () -> Unit = {},
        cancellable: Boolean = false,
        @DrawableRes icon: Int = R.drawable.ic_thumbs_up,
        @DrawableRes iconAction: Int = R.drawable.ic_done

    ) {
        AlertDialogFragment.Builder()
            .title(title)
            .description(description)
            .attachActionBlock(clickListener)
            .icon(icon)
            .iconAction(iconAction)
            .isCancellable(cancellable)
            .build()
            .show(supportFragmentManager, "")
    }

}