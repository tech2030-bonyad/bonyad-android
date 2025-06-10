package fudex.bonyad.viewmodel.user

import android.app.Activity
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
import fudex.bonyad.Model.OrdersModel
import fudex.bonyad.Model.OrdersDatum
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.ui.Adapter.user.Ordersadapter
import fudex.bonyad.ui.Fragment.technical.Refuse1Fragment
import fudex.bonyad.ui.Fragment.user.UserappointmentFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OrdersListViewModel(var catogaryFragment: UserappointmentFragment) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var status  = ObservableField<Int>(1)
    var orderList: ArrayList<OrdersDatum> = ArrayList()
    var context: UserappointmentFragment = UserappointmentFragment()
    var activity = Activity()
    var linearlayout: LinearLayoutManager? = null
    private val ordersadapter = Ordersadapter()
    var page = 1
    private var mLoading = false
    var addressId = 0
    var appointmentId = 0

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
        var statuses: ArrayList<Int>  = ArrayList()
        if (status.get() ==1) {
            statuses.add(1)
            statuses.add(2)
        }else if (status.get() == 3) {
            statuses.add(4)
        }else if (status.get() == 2) {
            statuses.add(3)
        }
        val call: Call<OrdersModel?>? = apiService.getusermyreservation(statuses,page,10)

        call?.enqueue(object : Callback<OrdersModel?> {
            override fun onResponse(call: Call<OrdersModel?>, response: Response<OrdersModel?>) {
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

            override fun onFailure(call: Call<OrdersModel?>, t: Throwable) {
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
        this.status.set(status)
        page = 1
        getorders()
    }
    fun makereject(){
        var fragment = Refuse1Fragment()
        fragment.setTargetFragment(context, 1)
        fragment.show(context.parentFragmentManager , "")
    }
    fun refuseorder(notes:String) {
        return
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java
        )
        var userdata = Userdata(notes = notes)
        val call: Call<ErrorResponse?>? =
            apiService.refusetechnicalappointment(appointmentId,userdata)
        call?.enqueue(object : Callback<ErrorResponse?> {
            override fun onResponse(
                call: Call<ErrorResponse?>,
                response: Response<ErrorResponse?>
            ) {
                if (response.code() == 200 || response.code() == 201) {
                    for (index in orderList){
                        if (index.id == appointmentId){
                            orderList.remove(index)
                            notifyChange()
                            break
                        }
                    }
                }else{
                    val errorText = response.errorBody()?.string() ?: "{}"
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            refuseorder(notes)
                        }
                    })
                }
                isloading.set(false)
                notifyChange()
            }

            override fun onFailure(call: Call<ErrorResponse?>, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection), activity)
                isloading.set(false)

            }
        })
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