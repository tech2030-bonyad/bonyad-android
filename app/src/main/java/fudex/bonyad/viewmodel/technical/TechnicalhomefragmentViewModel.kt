package fudex.bonyad.viewmodel.technical

import android.app.Activity
import android.content.Intent
import android.os.Handler
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.LinearLayoutManager
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson

import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Model.OrdersDatum
import fudex.bonyad.Model.TechnicalHomeModel
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.ui.Activity.NotificationsActivity
import fudex.bonyad.ui.Activity.technical.TechnicalHomeActivity
import fudex.bonyad.ui.Adapter.technical.TechnicalhomeOrdersadapter
import fudex.bonyad.ui.Adapter.user.Sliderhomedadapter
import fudex.bonyad.ui.Adapter.user.Technicaladapter
import fudex.bonyad.ui.Fragment.technical.TechnicalhomeFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import kotlin.collections.ArrayList


class TechnicalhomefragmentViewModel(context: TechnicalhomeFragment) : BaseObservable() {
    var context: TechnicalhomeFragment = TechnicalhomeFragment()
    var activity = Activity()
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var isenable: ObservableBoolean = ObservableBoolean(false)
    var homedata = ObservableField<TechnicalHomeModel>()
    var newList : ArrayList<OrdersDatum> = ArrayList()
    var perviousList : ArrayList<OrdersDatum> = ArrayList()
    var day = 6
    private val upcomingadapter = TechnicalhomeOrdersadapter()
    private val perviousadapter = TechnicalhomeOrdersadapter()
    var handler: Handler = Handler()
    lateinit var pagerAdapter: Sliderhomedadapter

    init {
        this.context = context
        activity = context.requireActivity()
        var linearlayout = LinearLayoutManager(activity)
        linearlayout!!.orientation = LinearLayoutManager.VERTICAL
        context.binding.orderList.layoutManager = linearlayout
        context.binding.orderList.adapter = upcomingadapter
        var linearlayout1 = LinearLayoutManager(activity)
        linearlayout1!!.orientation = LinearLayoutManager.VERTICAL
        context.binding.perviousorderList.layoutManager = linearlayout1
        context.binding.perviousorderList.adapter = perviousadapter
        gethome()
    }

    fun gethome() {
        context.binding.orderList.showShimmer()
        context.binding.perviousorderList.showShimmer()
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)
        var call: Call<TechnicalHomeModel?>? = null
        call = apiService.gettechnicalhome()
        call?.enqueue(object : Callback<TechnicalHomeModel?> {
            override fun onResponse(call: Call<TechnicalHomeModel?>, response: Response<TechnicalHomeModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    homedata.set(data)
                    perviousList.clear()
                    newList.clear()
                    perviousList.addAll(data?.data!!.previous_reservations!!)
                    newList.addAll(data?.data!!.upcoming_reservations!!)
                    notifyChange()
                }else {
                    val errorText = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            gethome()
                        }
                    })
                }
                context.binding.orderList.hideShimmer()
                context.binding.perviousorderList.hideShimmer()
                isloading.set(false)
            }

            override fun onFailure(call: Call<TechnicalHomeModel?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                isloading.set(false)
                context.binding.orderList.hideShimmer()
                context.binding.perviousorderList.hideShimmer()
            }
        })
    }
    fun more(){
        if (activity is TechnicalHomeActivity){
            (activity as TechnicalHomeActivity).technicalhomeViewModel.appointment()
        }
    }
    fun not(){
        var intent: Intent = Intent(context?.requireActivity(), NotificationsActivity::class.java)
        context?.startActivity(intent)
    }

}
