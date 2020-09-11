package com.example.groomexprez.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.groomexprez.R

class ProgressDialogFragment : DialogFragment() {
    private var tvTitle: TextView? = null
    private var dismissListener: OnDismissListener? = null
    private var cancellable = false
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        checkNotNull(activity) { "Invalid State; getActivity() is null" }

        val inflater = requireActivity().layoutInflater
        val view: View = inflater.inflate(R.layout.dialog_progress_bar, null)
        init(view)
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(view).setCancelable(cancellable)
        val dialog: Dialog = builder.create()
        val args = arguments ?: throw IllegalArgumentException("required arguments are null")
        val title = args.getString("title")
        if (TextUtils.isEmpty(title)) {
            tvTitle!!.visibility = View.GONE
        } else {
            tvTitle!!.text = title
        }
        dialog.setOnDismissListener { dialogInterface: DialogInterface? ->
            dismissListener!!.onDismiss(
                dialogInterface
            )
        }
//        if (dialog.window != null && !BuildConfig.DEBUG) dialog.window!!
//            .setFlags(
//                WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE
//            )
        return dialog
    }

    private fun init(view: View) {
        tvTitle = view.findViewById(R.id.text_view)
    }

    private fun setDismissListener(listener: OnDismissListener) {
        dismissListener = listener
    }

    private fun isCancelable(cancelable: Boolean) {
        cancellable = cancelable
    }

    class Builder(private val mDismissListener: OnDismissListener) {
        private var mTitle: String? = null
        private var mIsCancelable = false
        fun setTitle(title: String?): Builder {
            mTitle = title
            return this
        }

        fun setCancelable(cancelable: Boolean): Builder {
            mIsCancelable = cancelable
            return this
        }

        fun build(): ProgressDialogFragment {
            val fragment =
                ProgressDialogFragment()
            val bundle = Bundle()
            bundle.putString("title", if (mTitle != null) mTitle else "")
            fragment.arguments = bundle
            fragment.setDismissListener(mDismissListener)
            fragment.isCancelable(mIsCancelable)
            fragment.isCancelable = mIsCancelable
            return fragment
        }

    }

    interface OnDismissListener {
        fun onDismiss(dialog: DialogInterface?)
    }
}
