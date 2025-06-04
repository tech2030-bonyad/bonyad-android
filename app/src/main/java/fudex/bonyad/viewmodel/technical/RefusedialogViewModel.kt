package fudex.bonyad.viewmodel.technical

import android.annotation.SuppressLint
import android.app.Activity
import android.text.InputFilter
import android.util.Log
import android.view.Gravity
import android.widget.PopupMenu
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.adapters.TextViewBindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Helper.Camera.Companion.activity
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.Model.AddressesDatum
import fudex.bonyad.Model.ServicestypeModel
import fudex.bonyad.Model.ServicetypeDatum
import fudex.bonyad.Model.StatesModel
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.ui.Fragment.technical.RefuseFragment
import fudex.bonyad.ui.Fragment.user.RatingdialogFragment
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RefusedialogViewModel(var catogaryFragment: RefuseFragment) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var context: RefuseFragment = RefuseFragment()
    var activity = Activity()
    val commentObserv = ObservableField<String>()
    @SuppressLint("RestrictedApi")
    fun oncommentChanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            commentObserv.set(s.toString())
        }
    }

    init {
        this.context = catogaryFragment
        activity = context.requireActivity()
        if (activity.getString(R.string.lang) == "ar"){
            context.binding.comment.gravity = Gravity.RIGHT
        }
        context.binding.main.setOnClickListener {
            Utilities.closeKeyboard(activity)
        }
    }
    fun dismiss(){
        context.dismiss()
    }

    fun validateInput() {
        var error = false
        if (commentObserv.get() == null || commentObserv.get()!!.isEmpty()) {
            error = true
            context.binding.comment.setError(activity.getString(R.string.required))
        }
        if (!error) {
        context.dialogListener?.onDataReceived("refuse" + context.binding.comment.text.toString())
        context.dismiss()
        }

    }

}