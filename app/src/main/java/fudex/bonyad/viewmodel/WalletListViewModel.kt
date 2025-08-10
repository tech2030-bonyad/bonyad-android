package fudex.bonyad.viewmodel


import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.LinearLayoutManager
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Data.Chargestatus
import fudex.bonyad.Data.Orderdata
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.Model.CartModel
import fudex.bonyad.Model.NotsModel
import fudex.bonyad.Model.CreateorderModel
import fudex.bonyad.Model.SubsribeModel
import fudex.bonyad.Model.TransactionsDatum
import fudex.bonyad.Model.TransactionsModel
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.ui.Activity.RatingActivity
import fudex.bonyad.ui.Activity.WalletActivity
import fudex.bonyad.ui.Activity.WebviewActivity
import fudex.bonyad.ui.Activity.user.LocationmapActivity
import fudex.bonyad.ui.Activity.user.UserhomeActivity
import fudex.bonyad.ui.Adapter.Transactionsadapter
import fudex.bonyad.ui.Adapter.user.Cartproductadapter
import fudex.bonyad.ui.Fragment.DeletetFragment
import fudex.bonyad.ui.Fragment.WalletchargeFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WalletListViewModel(var catogaryFragment: WalletActivity) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var context: WalletActivity = WalletActivity()
    var transactionsList: ArrayList<TransactionsDatum> = ArrayList()
    var page = 1
    var wallet = ObservableField<Double>(0.0)
    private var mLoading = false
    private val transactionsadapter = Transactionsadapter()

    init {
        this.context = catogaryFragment
        var linearlayout = LinearLayoutManager(context)
        linearlayout!!.orientation = LinearLayoutManager.VERTICAL
        context.binding.walletList.layoutManager = linearlayout
        context.binding.walletList.adapter = transactionsadapter
        getransactionss()
        getbalance()
        context.binding.scroll.setOnScrollChangeListener { _, _, scrollY, _, _ ->
            if (scrollY == context.binding.scroll.getChildAt(0).measuredHeight - context.binding.scroll.height) {
                if (!mLoading) {
                    mLoading = true
                    getransactionss()
                }
            }
        }
    }
    fun getransactionss() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java
        )

        val call: Call<TransactionsModel?>? =
            apiService.gettransactions(page,10)
        call?.enqueue(object : Callback<TransactionsModel?> {
            override fun onResponse(
                call: Call<TransactionsModel?>,
                response: Response<TransactionsModel?>
            ) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    if (page == 1) {
                        transactionsList.clear()
                    }
                    transactionsList.addAll(data?.data!!)
                    if (response.body()!!.data!!.size > 0) {
                        page++
                        mLoading = false
                    }
                    notifyChange()
                } else {
                    val errorText = response.errorBody()?.string() ?: "{}"
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(
                        context,
                        response.code(),
                        errorResponse,
                        object : APIModel.RefreshTokenListener {
                            override fun onRefresh() {
                                getransactionss()
                            }
                        })
                }
                isloading.set(false)
            }

            override fun onFailure(call: Call<TransactionsModel?>, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection), context)
                isloading.set(false)

            }
        })
    }

    fun withdraw(){
        Utilities.disabletouch(context)
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java)
        val call: Call<ErrorResponse?>? = apiService.withdrawewallet(Chargestatus(wallet.get().toString()))
        call?.enqueue(object : Callback<ErrorResponse?> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<ErrorResponse?>, response: Response<ErrorResponse?>) {
                if (response.code() == 200 || response.code() == 201) {
                    Utilities.showWithdrwasDialog(context,
                        context.getString(R.string.your_request_has_been_successfully) ,
                        context.getString(R.string.and_is_now_under_review_you_will_be_contacted_after_the_request_is_approved)){

                    }
                } else{
                    val errorText = response.errorBody()?.string() ?: "{}"
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(context, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            withdraw()
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
    fun charge(money:String){
        Utilities.disabletouch(context)
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java)
        val call: Call<SubsribeModel?>? = apiService.chargewallet(Chargestatus(money))
        call?.enqueue(object : Callback<SubsribeModel?> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<SubsribeModel?>, response: Response<SubsribeModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var intent: Intent = Intent(context, WebviewActivity::class.java)
                    intent.putExtra("url",response.body()!!.data?.redirectUrl ?: "")
                    intent.putExtra("wallet","")
//                    intent.putExtra("success",response.body()!!.data?.success_url ?: "")
//                    intent.putExtra("fail",response.body()!!.data?.fail_url ?: "")
                    context.startActivity(intent)
                } else{
                    val errorText = response.errorBody()?.string() ?: "{}"
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(context, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            charge(money)
                        }
                    })
                }
                isloading.set(false)
                Utilities.enabletouch(context)

            }

            override fun onFailure(call: Call<SubsribeModel?>, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection) , context)
                isloading.set(false)
                Utilities.enabletouch(context)
            }
        })
    }

    fun back(){
        context.onBackPressed()
    }


    fun getbalance() {
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java)

        val call: Call<NotsModel?>? = apiService.getwalletbalance()
        call?.enqueue(object : Callback<NotsModel?> {
            override fun onResponse(call: Call<NotsModel?>, response: Response<NotsModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    wallet.set(data?.balance ?: 0.0)
                    notifyChange()
                }else {
                    val errorText = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(context, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            getbalance()
                        }
                    })
                }

            }

            override fun onFailure(call: Call<NotsModel?>, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection) , context)
            }
        })
    }
    fun charge(){
        var fragment = WalletchargeFragment()
        fragment.show(context.supportFragmentManager , "charge")
    }

    fun withdrawdialog(){
        var fragment = DeletetFragment()
        fragment.show(context.supportFragmentManager , "withdraw")
    }
}