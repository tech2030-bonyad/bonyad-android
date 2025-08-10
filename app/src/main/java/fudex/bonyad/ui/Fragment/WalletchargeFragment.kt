package fudex.bonyad.ui.Fragment

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
import fudex.bonyad.databinding.ChargewalletviewModelBinding
import fudex.bonyad.viewmodel.ChargewalletViewModel


class WalletchargeFragment : DialogFragment() {

    lateinit var chargewalletViewModel: ChargewalletViewModel
    lateinit var binding : ChargewalletviewModelBinding
    var dialogListener: DialogListener? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            dialogListener = context as DialogListener
        } catch (e: ClassCastException) {
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_walletcharge, container, false)
        chargewalletViewModel = ChargewalletViewModel(this@WalletchargeFragment)
        binding.model = chargewalletViewModel
        binding.main.setOnClickListener {
            Utilities.closeKeyboard(this.requireActivity())
        }
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