package com.example.groomexprez.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.groomexprez.R
import com.example.groomexprez.databinding.DialogLayoutAlertBinding

class AlertDialogFragment : DialogFragment() {

    private var _binding: DialogLayoutAlertBinding? = null
    private val binding get() = _binding
    var actionBlock: () -> Unit = {}
    var mHasSuccessBg: Boolean = false
    var hasDescription: Boolean = false

    @Override
    override fun onResume() {
        super.onResume()
    }

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    @Override
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        checkNotNull(activity) { "Invalid State; getActivity() is null" }

        _binding = DialogLayoutAlertBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(activity ?: return dialog!!)
        builder.setView(binding?.root)
        val dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        dialog.window?.setGravity(Gravity.CENTER)

        init()

        return dialog
    }

    private fun init() {

        arguments?.apply {
            _binding?.ivSuccess?.setImageResource(getInt("icon"))
            _binding?.imgAction?.setImageResource(getInt("iconAction"))
            _binding?.tvAlertTitle1?.text = getString("title")
            _binding?.tvDescription?.text = getString("description")

            _binding?.imgAction?.setOnClickListener {
                dismiss()
                actionBlock()
            }
        }
    }


        class Builder {
            private var actionBlock: () -> Unit = {}
            private var title = ""
            private var description = ""
            private var icon = R.drawable.ic_thumbs_up
            private var iconAction = R.drawable.ic_done
            private var isCancellable = false

            fun attachActionBlock(block: () -> Unit): Builder {
                actionBlock = block
                return this
            }

            fun icon(icon: Int): Builder {
                this.icon = icon
                return this
            }

            fun title(title: String): Builder {
                this.title = title
                return this
            }

            fun description(description: String): Builder {
                this.description = description
                return this
            }

            fun iconAction(iconAction: Int): Builder {
                this.iconAction = iconAction
                return this
            }

            fun isCancellable(isCancellable: Boolean): Builder {
                this.isCancellable = isCancellable
                return this
            }

            fun build(): AlertDialogFragment {
                val fragment =
                    AlertDialogFragment()
                val bundle = Bundle()


                bundle.putString("title", title)
                bundle.putString("description", description)
                bundle.putInt("icon", icon)
                bundle.putInt("iconAction", iconAction)

                fragment.arguments = bundle
                fragment.isCancelable = isCancellable
                fragment.actionBlock = actionBlock
                return fragment
            }
        }

}




