package fudex.bonyad.viewmodel.user

import android.app.Activity
import android.widget.PopupMenu
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.LinearLayoutManager
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Model.FilterModel
import fudex.bonyad.Model.StatesDatum
import fudex.bonyad.Model.StatesModel
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.ui.Adapter.user.Technicafilterladapter
import fudex.bonyad.ui.Fragment.user.FilterspecialFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FiltertechnicalViewModel(var catogaryFragment: FilterspecialFragment) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var context: FilterspecialFragment = FilterspecialFragment()
    var activity = Activity()
    val zonename = ObservableField<String>("")
    var zoneId = 0
    var zoneList: ArrayList<StatesDatum> = ArrayList()
    var servicesList: ArrayList<StatesDatum> = ArrayList()
    var serviceId = 0
    private val technicafilterladapter = Technicafilterladapter()

    init {
        this.context = catogaryFragment
        activity = context.requireActivity()
        var linearlayout = LinearLayoutManager(activity)
        linearlayout!!.orientation = LinearLayoutManager.VERTICAL
        context.binding.serviceList.layoutManager = linearlayout
        context.binding.serviceList.adapter = technicafilterladapter
        val filter = Gson().fromJson(context.requireArguments().getString("filter","{}"), FilterModel::class.java)
        zoneId = filter.cityId ?: 0
        serviceId = filter.serviceId ?: 0
        getzones()
        getservices()
    }

    fun getzones() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)
        var call: Call<StatesModel?>? = null
        call = apiService.getzones()
        call?.enqueue(object : Callback<StatesModel?> {
            override fun onResponse(call: Call<StatesModel?>, response: Response<StatesModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    zoneList.clear()
                    zoneList.addAll(data?.data!!)
                    for (itemm in zoneList){
                        if (zoneId == itemm.id ?: 0){
                            zonename.set(itemm.name ?: "")
                        }
                    }
                }else {
                    val errorText = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            getzones()
                        }
                    })
                }

                isloading.set(false)
            }

            override fun onFailure(call: Call<StatesModel?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                isloading.set(false)
            }
        })
    }
    fun getservices() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)
        var call: Call<StatesModel?>? = null
        call = apiService.getservices(null,null,null)
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
             //   context.binding.serviceList.hideShimmer()
                isloading.set(false)
            }

            override fun onFailure(call: Call<StatesModel?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                isloading.set(false)
               // context.binding.serviceList.hideShimmer()
            }
        })
    }
    fun zoneclick() {
        var secPopUp = PopupMenu(activity, context.binding.cityLin)
        for (i in zoneList.indices) {
            secPopUp?.menu?.add(i, i, i, zoneList[i].name)

        }
        secPopUp?.setOnMenuItemClickListener { item ->
            zonename.set(zoneList[item.itemId].name!!)
            zoneId = zoneList[item.itemId].id!!
            false
        }
        secPopUp.show()
    }
    fun clearall(){
        zonename.set("")
        zoneId = 0
        serviceId = 0
        notifyChange()
    }
    fun save(){
        var filter = FilterModel(serviceId,zoneId)
        context.dialogListener?.onDataReceived("filter" + Gson().toJson(filter))
        context.dismiss()
    }
}