package fudex.bonyad.viewmodel.merchant

import android.app.Activity
import android.os.Handler
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Model.MerchantHomeModel
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.ui.Activity.merchant.StatisticsActivity
import fudex.bonyad.ui.Adapter.user.Sliderhomedadapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import kotlin.collections.ArrayList


class StatisticsViewModel(context: StatisticsActivity) : BaseObservable() {
    var context: StatisticsActivity = StatisticsActivity()
    var activity = Activity()
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var isenable: ObservableBoolean = ObservableBoolean(false)
    var homedata = ObservableField<MerchantHomeModel>()
    var day = 6
    var handler: Handler = Handler()
    lateinit var pagerAdapter: Sliderhomedadapter

    init {
        this.context = context
        gethome()
    }
    fun back(){
        context.onBackPressed()
    }
    fun gethome() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)
        var call: Call<MerchantHomeModel?>? = null
        call = apiService.getmerchanthome()
        call?.enqueue(object : Callback<MerchantHomeModel?> {
            override fun onResponse(call: Call<MerchantHomeModel?>, response: Response<MerchantHomeModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    homedata.set(data)
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
                isloading.set(false)
            }

            override fun onFailure(call: Call<MerchantHomeModel?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                isloading.set(false)

            }
        })
    }


}
