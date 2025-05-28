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
import fudex.bonyad.NetWorkConnction.DialogListener
import fudex.bonyad.R
import fudex.bonyad.databinding.ReservedoneviewModelBinding
import fudex.bonyad.viewmodel.user.ReservationDoneViewModel

class SpeciallistreservedoneFragment : DialogFragment() {

    lateinit var reservationDoneViewModel: ReservationDoneViewModel
    lateinit var binding : ReservedoneviewModelBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_speciallistreservedone, container, false)
        reservationDoneViewModel = ReservationDoneViewModel(this@SpeciallistreservedoneFragment)
        binding.model = reservationDoneViewModel
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.let { dialog ->
            dialog.window?.setBackgroundDrawableResource(R.drawable.rectrans)
            dialog.window?.setGravity(Gravity.CENTER)
            dialog.setCancelable(false)
            val displayMetrics = DisplayMetrics()
            requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
            val widthScreen = displayMetrics.widthPixels - 120
            val width = widthScreen
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window?.setLayout(width, height)
        }
    }
}