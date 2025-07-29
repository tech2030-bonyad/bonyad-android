package fudex.bonyad.viewmodel.merchant

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import androidx.annotation.RequiresApi
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.Model.ProductsDatum
import fudex.bonyad.Model.ProductsModel
import fudex.bonyad.Model.UserModel
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.ui.Activity.merchant.AddproductActivity
import fudex.bonyad.ui.Activity.merchant.MostproductsActivity
import fudex.bonyad.ui.Activity.merchant.MyproductActivity
import fudex.bonyad.ui.Adapter.merchant.Mostproductsadapter
import fudex.bonyad.ui.Adapter.merchant.Myproductsadapter

import fudex.bonyad.ui.Fragment.DeletetFragment

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MostproductsViewModel(var catogaryFragment: MostproductsActivity) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var context: MostproductsActivity = MostproductsActivity()
    var img = ObservableField<String>("")
    var islogin = ObservableField<Boolean>(false)
    var userdata = ObservableField<UserModel>()
    var productsList: ArrayList<ProductsDatum> = ArrayList()
    var  linearlayout: LinearLayoutManager? = null
    private val myproductsadapter = Mostproductsadapter()
    var handler: Handler = Handler()
    var page = 1
    private var mLoading = false
    var productId = 0
    init {
        this.context = catogaryFragment
        linearlayout = GridLayoutManager(context,2)
        context.binding.productList.layoutManager = linearlayout
        context.binding.productList.adapter = myproductsadapter
        scroll()
    }
    fun back(){
        context.onBackPressed()
    }
    fun getmyproducts() {
        if (page == 1) {
            context.binding.productList.showShimmer()
        }
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java)
        var call: Call<ProductsModel?>? = null
        call = apiService.getmostproducts(page,10)
        call?.enqueue(object : Callback<ProductsModel?> {
            override fun onResponse(call: Call<ProductsModel?>, response: Response<ProductsModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    if (page == 1) {
                        productsList.clear()
                    }
                    productsList.addAll(data?.data!!)
                    if (response.body()!!.data!!.size > 0) {
                        page++
                        mLoading = false
                    }
                    notifyChange()
                }else {
                    val errorText = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(context, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            getmyproducts()
                        }
                    })
                }
                context.binding.productList.hideShimmer()
                isloading.set(false)
            }

            override fun onFailure(call: Call<ProductsModel?>, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection) , context)
                isloading.set(false)
                context.binding.productList.hideShimmer()
            }
        })
    }
    fun scroll() {
        context.binding.productList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount: Int = linearlayout!!.getItemCount()
                val visibleItemCount: Int = linearlayout!!.findLastVisibleItemPosition()
                Log.e("pos", visibleItemCount.toString())
                if (!mLoading && visibleItemCount >= totalItemCount - 3 && page > 1) {
                    mLoading = true
                    getmyproducts()
                }

                super.onScrolled(recyclerView, dx, dy)
            }


        })

    }
}