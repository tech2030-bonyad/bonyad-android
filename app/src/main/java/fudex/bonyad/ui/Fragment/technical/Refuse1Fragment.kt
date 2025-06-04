package fudex.bonyad.ui.Fragment.technical

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import fudex.bonyad.NetWorkConnction.DialogListener
import fudex.bonyad.R
import fudex.bonyad.databinding.Refuse1ViewModelBinding
import fudex.bonyad.viewmodel.technical.Refusedialog1ViewModel


class Refuse1Fragment : DialogFragment() {
    lateinit var refusedialogViewModel: Refusedialog1ViewModel
    lateinit var binding : Refuse1ViewModelBinding
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_refuse1, container, false)
        refusedialogViewModel = Refusedialog1ViewModel(this@Refuse1Fragment)
        binding.model = refusedialogViewModel
        return binding.root
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