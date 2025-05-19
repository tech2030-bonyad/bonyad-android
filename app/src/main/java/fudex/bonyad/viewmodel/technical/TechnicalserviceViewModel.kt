package fudex.bonyad.viewmodel.technical

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.Model.StatesDatum
import fudex.bonyad.Model.StatesModel
import fudex.bonyad.Model.UserModel
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Activity.ChangelangueActivity

import fudex.bonyad.ui.Activity.ContactusActivity

import fudex.bonyad.ui.Activity.LoginActivity
import fudex.bonyad.ui.Activity.ProfileActivity
import fudex.bonyad.ui.Activity.StaticpageActivity
import fudex.bonyad.ui.Adapter.technical.Imagessadapter
import fudex.bonyad.ui.Adapter.technical.Serviceadapter
import fudex.bonyad.ui.Fragment.technical.TechnicalprofileFragment
import fudex.bonyad.ui.Fragment.technical.TechnicalservicesFragment
import fudex.bonyad.ui.Fragment.user.UserprofileFragment
import okhttp3.MultipartBody
import okhttp3.RequestBody

import onnetysolutions.sadded.Model.ProfileModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TechnicalserviceViewModel(var catogaryFragment: TechnicalservicesFragment) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var context: TechnicalservicesFragment = TechnicalservicesFragment()
    var activity = Activity()
    var img = ObservableField<String>("")
    var islogin = ObservableField<Boolean>(false)
    var userdata = ObservableField<UserModel>()
    var servicesList: ArrayList<StatesDatum> = ArrayList()
    var  linearlayout: LinearLayoutManager? = null
    private val serviceadapter = Serviceadapter()
    var handler: Handler = Handler()

    init {
        this.context = catogaryFragment
        activity = context.requireActivity()
        linearlayout = GridLayoutManager(activity,4)
        linearlayout!!.orientation = LinearLayoutManager.HORIZONTAL
        context.binding.serviceList.layoutManager = linearlayout
        context.binding.serviceList.adapter = serviceadapter
        if (activity.getString(R.string.lang) == "ar"){
            context.binding.searchTxt.gravity = Gravity.RIGHT
        }
        context.binding.searchTxt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                if (handler != null) handler.removeCallbacksAndMessages(null)
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//                if (edit.getText().toString().equals("")){
//                    subCategoriesBeans.clear();
//                    subCatogaryadapter.notifyDataSetChanged();
//                }else {
//                    gethome();
//                }
            }

            override fun afterTextChanged(s: Editable) {
                handler.postDelayed(Runnable {
                    getservices()
                }, 1000)
            }
        })
    }
    fun getservices() {
        context.binding.serviceList.showShimmer()
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)
        var call: Call<StatesModel?>? = null
        call = apiService.getmyservices(if (context.binding.searchTxt.text.toString() == ""){null}else{context.binding.searchTxt.text.toString()})
        call?.enqueue(object : Callback<StatesModel?> {
            override fun onResponse(call: Call<StatesModel?>, response: Response<StatesModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    servicesList.clear()
                    servicesList.addAll(data?.data!!)
                    notifyChange()
                }else {
                    val errorText = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            getservices()
                        }
                    })
                }
                context.binding.serviceList.hideShimmer()
                isloading.set(false)
            }

            override fun onFailure(call: Call<StatesModel?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                isloading.set(false)
                context.binding.serviceList.hideShimmer()
            }
        }) }
}