package fudex.bonyad.viewmodel.merchant

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Data.Userdata
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Model.FilterorderModel
import fudex.bonyad.Model.MerchantOrdersModel
import fudex.bonyad.Model.OrdersDatum
import fudex.bonyad.Model.RecentOrder
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.ui.Adapter.merchant.Ordersadapter
import fudex.bonyad.ui.Fragment.merchant.FilterordersFragment
import fudex.bonyad.ui.Fragment.merchant.MerchantordersFragment
import fudex.bonyad.ui.Fragment.technical.Refuse1Fragment
import fudex.bonyad.ui.Fragment.user.FilterspecialFragment
import fudex.bonyad.ui.Fragment.user.UserappointmentFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OrdersListViewModel(var catogaryFragment: MerchantordersFragment) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var status  = ObservableField<Int>(1)
    var orderList: ArrayList<RecentOrder> = ArrayList()
    var context: MerchantordersFragment = MerchantordersFragment()
    var activity = Activity()
    var linearlayout: LinearLayoutManager? = null
    private val ordersadapter = Ordersadapter()
    var page = 1
    private var mLoading = false
    var addressId = 0
    var appointmentId = 0
    var from = ""
    var to = ""
    var status1 = 0
    var statuses: ArrayList<Int>  = ArrayList()

    init {
        this.context = catogaryFragment
        activity = context.requireActivity()
        linearlayout = LinearLayoutManager(activity)
        linearlayout!!.orientation = LinearLayoutManager.VERTICAL
        context.binding.orderList.layoutManager = linearlayout
        context.binding.orderList.adapter = ordersadapter
        scroll()
       // swiptorefresh()
    }

    fun getorders() {
        context.binding.orderList.showShimmer()
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)

        val call: Call<MerchantOrdersModel?>? = apiService.getmerchantorders(statuses,page,10,if (from == ""){null}else{from},if (to == ""){null}else{to})

        call?.enqueue(object : Callback<MerchantOrdersModel?> {
            override fun onResponse(call: Call<MerchantOrdersModel?>, response: Response<MerchantOrdersModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    if (page == 1) {
                        orderList.clear()
                    }
                    orderList.addAll(data?.data!!)
                    if (response.body()!!.data!!.size > 0) {
                        page++
                        mLoading = false
                    }
                    notifyChange()
                }else {
                    val errorText = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            getorders()
                        }
                    })
                }

                isloading.set(false)
                context.binding.orderList.hideShimmer()
            }

            override fun onFailure(call: Call<MerchantOrdersModel?>, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection) , activity)
                isloading.set(false)
                context.binding.orderList.hideShimmer()

            }
        })
    }

    fun scroll() {
        context.binding.orderList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount: Int = linearlayout!!.getItemCount()
                val visibleItemCount: Int = linearlayout!!.findLastVisibleItemPosition()
                Log.e("pos", visibleItemCount.toString())
                if (!mLoading && visibleItemCount >= totalItemCount - 3 && page > 1) {
                    mLoading = true
                    getorders()
                }

                super.onScrolled(recyclerView, dx, dy)
            }


        })

    }
    fun setstatus(status : Int){
        if (this.status.get() == status){
            return
        }
        statuses.clear()
        if (status ==1) {
            statuses.add(1)
            statuses.add(6)
            statuses.add(7)
            statuses.add(8)
        }else if (status == 3) {
            statuses.add(4)
        }
        from = ""
        to = ""
        status1 = 0
        this.status.set(status)
        page = 1
        getorders()
    }
    fun filter(){
        var fragment = FilterordersFragment()
        var bundle = Bundle()
        bundle.putString("filter",Gson().toJson(FilterorderModel(status1,from,to)))
        bundle.putInt("status",status.get() ?: 1)
        fragment.arguments = bundle
        fragment.setTargetFragment(context, 1)
        fragment.show(context.parentFragmentManager , "filter")
    }

//    fun swiptorefresh() {
//        context.binding.swip.setOnRefreshListener {
//            Handler().postDelayed(Runnable {
//                context.binding.swip.isRefreshing = false
//            }, 1500)
//            page = 1
//            getaddresses()
//        }
//    }
}