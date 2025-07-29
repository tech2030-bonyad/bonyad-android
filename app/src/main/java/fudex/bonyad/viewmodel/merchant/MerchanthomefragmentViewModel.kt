package fudex.bonyad.viewmodel.merchant

import android.app.Activity
import android.content.Intent
import android.os.Handler
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson

import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Model.MerchantHomeModel
import fudex.bonyad.Model.OrdersDatum
import fudex.bonyad.Model.ProductsDatum
import fudex.bonyad.Model.RecentOrder
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.ui.Activity.NotificationsActivity
import fudex.bonyad.ui.Activity.merchant.MerchanthomeActivity
import fudex.bonyad.ui.Activity.merchant.MostproductsActivity
import fudex.bonyad.ui.Activity.technical.TechnicalHomeActivity
import fudex.bonyad.ui.Adapter.merchant.Mostproductsadapter
import fudex.bonyad.ui.Adapter.merchant.Ordersadapter
import fudex.bonyad.ui.Adapter.technical.TechnicalhomeOrdersadapter
import fudex.bonyad.ui.Adapter.user.Sliderhomedadapter
import fudex.bonyad.ui.Fragment.merchant.MerchanthomeFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import kotlin.collections.ArrayList


class MerchanthomefragmentViewModel(context: MerchanthomeFragment) : BaseObservable() {
    var context: MerchanthomeFragment = MerchanthomeFragment()
    var activity = Activity()
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var isenable: ObservableBoolean = ObservableBoolean(false)
    var homedata = ObservableField<MerchantHomeModel>()
    var productsList : ArrayList<ProductsDatum> = ArrayList()
    var ordersList : ArrayList<RecentOrder> = ArrayList()
    var day = 6
    private val mostproductsadapter = Mostproductsadapter()
    private val ordersadapter = Ordersadapter()
    var handler: Handler = Handler()
    lateinit var pagerAdapter: Sliderhomedadapter

    init {
        this.context = context
        activity = context.requireActivity()
        var linearlayout = LinearLayoutManager(activity)
        linearlayout!!.orientation = LinearLayoutManager.VERTICAL
        context.binding.orderList.layoutManager = linearlayout
        context.binding.orderList.adapter = ordersadapter
        var linearlayout1 = GridLayoutManager(activity,2)
        linearlayout1!!.orientation = LinearLayoutManager.VERTICAL
        context.binding.productList.layoutManager = linearlayout1
        context.binding.productList.adapter = mostproductsadapter
        gethome()
    }

    fun gethome() {
        context.binding.orderList.showShimmer()
        context.binding.productList.showShimmer()
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)
        var call: Call<MerchantHomeModel?>? = null
        call = apiService.getmerchanthome()
        call?.enqueue(object : Callback<MerchantHomeModel?> {
            override fun onResponse(call: Call<MerchantHomeModel?>, response: Response<MerchantHomeModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
//                    if ((data?.data?.new_reservations?.trend ?: "") == "low"){
//                        data?.data?.new_reservations?.trend = "l"
//                    }
//                    if ((data?.data?.total_reservations?.trend ?: "") == "low"){
//                        data?.data?.total_reservations?.trend = "l"
//                    }
//                    if ((data?.data?.completed_reservations?.trend ?: "") == "low"){
//                        data?.data?.completed_reservations?.trend = "l"
//                    }
                    homedata.set(data)
                    ordersList.clear()
                    productsList.clear()
                    ordersList.addAll(data?.data!!.recent_orders!!)
                    productsList.addAll(data?.data!!.top_products!!)
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
                context.binding.productList.hideShimmer()
                isloading.set(false)
            }

            override fun onFailure(call: Call<MerchantHomeModel?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                isloading.set(false)
                context.binding.orderList.hideShimmer()
                context.binding.productList.hideShimmer()
            }
        })
    }
    fun more(){
        if (activity is MerchanthomeActivity){
            (activity as MerchanthomeActivity).merchanthomeViewModel.orders()
        }
    }
    fun moreproducts(){
        var intent: Intent = Intent(context?.requireActivity(), MostproductsActivity::class.java)
        context?.startActivity(intent)
    }
    fun not(){
        var intent: Intent = Intent(context?.requireActivity(), NotificationsActivity::class.java)
        context?.startActivity(intent)
    }

}
