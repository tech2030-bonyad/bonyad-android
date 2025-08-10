package fudex.bonyad.viewmodel

import android.annotation.SuppressLint
import android.app.Activity
import android.view.Gravity
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.adapters.TextViewBindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.R
import fudex.bonyad.ui.Activity.LoginActivity
import fudex.bonyad.ui.Fragment.WalletchargeFragment


@SuppressLint("UseRequireInsteadOfGet")
class ChargewalletViewModel(var catogaryFragment: WalletchargeFragment) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var context: WalletchargeFragment = WalletchargeFragment()
    var activity = Activity()
    var linearlayout: LinearLayoutManager? = null
    val moneyObserv = ObservableField<String>()
    @SuppressLint("RestrictedApi")
    fun onmoneyChanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            moneyObserv.set(s.toString())
        }
    }
    init {
        this.context = catogaryFragment
        activity = context.requireActivity()
        if (activity.getString(R.string.lang) == "ar"){
            context.binding.money.gravity = Gravity.RIGHT
        }
    }
    fun validateInput() {

        var error = false
        if (moneyObserv.get() == null || moneyObserv.get()!!.isEmpty()) {
            error = true
            context.binding.money.setError(activity.getString(R.string.required))
        }

        if (!error) {
            Utilities.closeKeyboard(activity)
            charge()
        }
    }
    fun charge(){
        context.dialogListener?.onDataReceived("charge" + moneyObserv.get().toString() ?: "")
        context.dismiss()
    }
    fun cancel(){
        context.dismiss()
    }
}