package fudex.bonyad.viewmodel.user

import android.os.Handler
import android.util.Log
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
import fudex.bonyad.Model.FilterModel
import fudex.bonyad.Model.ProductsDatum
import fudex.bonyad.Model.ProductsModel
import fudex.bonyad.Model.UserModel
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.ui.Activity.user.ProductsActivity
import fudex.bonyad.ui.Adapter.user.Productitemadapter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductslistViewModel(var catogaryFragment: ProductsActivity) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var context: ProductsActivity = ProductsActivity()
    var img = ObservableField<String>("")
    var islogin = ObservableField<Boolean>(false)
    var userdata = ObservableField<UserModel>()
    var productsList: ArrayList<ProductsDatum> = ArrayList()
    var  linearlayout: LinearLayoutManager? = null
    private val productitemadapter = Productitemadapter()
    var handler: Handler = Handler()
    var page = 1
    private var mLoading = false
    var serviceId = 0
    var zoneId = 0
    var filter = FilterModel(0,0)

    init {
        this.context = catogaryFragment
        linearlayout = GridLayoutManager(context,2)
        linearlayout!!.orientation = LinearLayoutManager.VERTICAL
        context.binding.productsList.layoutManager = linearlayout
        context.binding.productsList.adapter = productitemadapter
        scroll()

    }
    fun back(){
        context.onBackPressed()
    }
    fun getproducts() {
        if (page == 1) {
            context.binding.productsList.showShimmer()
        }
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java)
        var call: Call<ProductsModel?>? = null
        var catogaries: ArrayList<Int>  = ArrayList()
        if (context.intent.hasExtra("id") ){
            catogaries.add( context.intent.getIntExtra("id",0))
        }
        call = apiService.getproducts(if (catogaries.size == 0) { null }else { catogaries },page,10)
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
                            getproducts()
                        }
                    })
                }
                context.binding.productsList.hideShimmer()
                isloading.set(false)
            }

            override fun onFailure(call: Call<ProductsModel?>, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection) , context)
                isloading.set(false)
                context.binding.productsList.hideShimmer()
            }
        })
    }
    fun scroll() {
        context.binding.productsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount: Int = linearlayout!!.getItemCount()
                val visibleItemCount: Int = linearlayout!!.findLastVisibleItemPosition()
                Log.e("pos", visibleItemCount.toString())
                if (!mLoading && visibleItemCount >= totalItemCount - 3 && page > 1) {
                    mLoading = true
                    getproducts()
                }

                super.onScrolled(recyclerView, dx, dy)
            }


        })

    }

}