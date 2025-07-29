package fudex.bonyad.ui.Fragment.merchant

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.DialogCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import fudex.bonyad.NetWorkConnction.DialogListener
import fudex.bonyad.R
import fudex.bonyad.databinding.FilterordersViewModelBinding
import fudex.bonyad.ui.Fragment.user.FilterspecialFragment
import fudex.bonyad.viewmodel.merchant.FilterorderViewModel

class FilterordersFragment : DialogFragment() {
    lateinit var filterorderViewModel: FilterorderViewModel
    lateinit var binding : FilterordersViewModelBinding
    var dialogListener: DialogListener? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            dialogListener = targetFragment as? DialogListener
        } catch (e: ClassCastException) {
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_filterorders, container, false)
        filterorderViewModel = FilterorderViewModel(this@FilterordersFragment)
        binding.model = filterorderViewModel
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.let { dialog ->
            dialog.window?.setBackgroundDrawableResource(R.drawable.rectrans)
            dialog.window?.setGravity(Gravity.CENTER)
            val displayMetrics = DisplayMetrics()
            requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
            val widthScreen = displayMetrics.widthPixels - 40
            val width = widthScreen
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window?.setLayout(width, height)
        }
    }


}