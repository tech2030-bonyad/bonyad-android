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
import fudex.bonyad.ui.Activity.merchant.MyproductActivity
import fudex.bonyad.ui.Adapter.merchant.Myproductsadapter

import fudex.bonyad.ui.Fragment.DeletetFragment

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyproductsViewModel(var catogaryFragment: MyproductActivity) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var context: MyproductActivity = MyproductActivity()
    var img = ObservableField<String>("")
    var islogin = ObservableField<Boolean>(false)
    var userdata = ObservableField<UserModel>()
    var productsList: ArrayList<ProductsDatum> = ArrayList()
    var  linearlayout: LinearLayoutManager? = null
    private val myproductsadapter = Myproductsadapter()
    var handler: Handler = Handler()
    var page = 1
    private var mLoading = false
    var productId = 0
    init {
        this.context = catogaryFragment
        linearlayout = GridLayoutManager(context,2)
        context.binding.productList.layoutManager = linearlayout
        context.binding.productList.adapter = myproductsadapter
        if (context.getString(R.string.lang) == "ar"){
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
                    page = 1
                    getmyproducts()
                }, 1000)
            }
        })
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
        call = apiService.getmyproducts(if (context.binding.searchTxt.text.toString() == ""){null}else{context.binding.searchTxt.text.toString()},page,10)
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
    fun deleteproduct(id: Int){
        Utilities.disabletouch(context)
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java)
        var serviceids: ArrayList<Int> = ArrayList()
        serviceids.add(id)
        val call: Call<ErrorResponse?>? = apiService.deleteproduct(id.toString())
        call?.enqueue(object : Callback<ErrorResponse?> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<ErrorResponse?>, response: Response<ErrorResponse?>) {
                if (response.code() == 200 || response.code() == 201) {
                    Dialogs.showToast(response.body()!!.message ?: "" , context)
                    var index = 0
                    for (item in productsList){
                        if (item.id == id){
                            productsList.removeAt(index)
                        }
                        index = index + 1
                    }
                    notifyChange()
                } else{
                    val errorText = response.errorBody()?.string() ?: "{}"
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(context, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            deleteproduct(id)
                        }
                    })
                }
                isloading.set(false)
                Utilities.enabletouch(context)

            }

            override fun onFailure(call: Call<ErrorResponse?>, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection) , context)
                isloading.set(false)
                Utilities.enabletouch(context)
            }
        })
    }
    fun delete(){
        var fragment = DeletetFragment()
        var bundle = Bundle()
        fragment.arguments = bundle
        fragment.show(context.supportFragmentManager , "deleteproduct")
    }
    fun addproduct(){
        var intent: Intent = Intent(context, AddproductActivity::class.java)
        context?.startActivity(intent)
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