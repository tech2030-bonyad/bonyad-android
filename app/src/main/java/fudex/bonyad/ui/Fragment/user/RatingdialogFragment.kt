package fudex.bonyad.ui.Fragment.user

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.NetWorkConnction.DialogListener
import fudex.bonyad.R
import fudex.bonyad.databinding.RatingViewModelBinding
import fudex.bonyad.viewmodel.user.RatingdialogViewModel


class RatingdialogFragment : DialogFragment() {
    lateinit var ratingdialogViewModel: RatingdialogViewModel
    lateinit var binding : RatingViewModelBinding
    var dialogListener: DialogListener? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            dialogListener = context as? DialogListener
        } catch (e: ClassCastException) {
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ratingdialog, container, false)
        ratingdialogViewModel = RatingdialogViewModel(this@RatingdialogFragment)
        binding.model = ratingdialogViewModel
        binding.main.setOnClickListener {
            it.hideKeyboard()
        }
        return binding.root
    }
    fun View.hideKeyboard() {
        Utilities.closeKeyboard(ratingdialogViewModel.activity)
    }
    override fun onStart() {
        super.onStart()
        dialog?.let { dialog ->
            dialog.window?.setBackgroundDrawableResource(R.drawable.rectrans)
            dialog.window?.setGravity(Gravity.BOTTOM)
            val displayMetrics = DisplayMetrics()
            requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
            val widthScreen = displayMetrics.widthPixels - 40
            val width = widthScreen
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window?.setLayout(width, height)
        }
    }

}