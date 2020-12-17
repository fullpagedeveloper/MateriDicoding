package com.fullpagedeveloper.fragment.senddatainfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.fullpagedeveloper.fragment.R
import kotlinx.android.synthetic.main.fragment_option_dialog.*
import kotlinx.android.synthetic.main.fragment_option_dialog.view.*

class OptionDialogFragment : DialogFragment(), View.OnClickListener {

    private var optionDialogListener: OnOptionDialogListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.btn_close.setOnClickListener(this@OptionDialogFragment)
        view.btn_choose.setOnClickListener(this@OptionDialogFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val fragment = parentFragment

        if (fragment is DetailCategoryFragment) {
            this.optionDialogListener = fragment.optionDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.optionDialogListener = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_close -> dialog?.cancel()
            R.id.btn_choose -> {
                val checkedRadioButtonId = rg_option.checkedRadioButtonId
                if (checkedRadioButtonId != -1) {
                    var coach: String? = null
                    when (checkedRadioButtonId) {
                        R.id.rb_saf -> coach = rb_saf.text.toString().trim()
                        R.id.rb_mou -> coach = rb_mou.text.toString().trim()
                        R.id.rb_lvg -> coach = rb_lvg.text.toString().trim()
                        R.id.rb_moyes -> coach = rb_moyes.text.toString().trim()
                    }
                    if (optionDialogListener != null) {
                        if (coach != null) {
                            optionDialogListener?.onOptionChosen(coach)
                        }
                    }
                    dialog?.dismiss()
                }
            }
        }
    }

    interface OnOptionDialogListener{
        fun onOptionChosen(text: String)
    }
}