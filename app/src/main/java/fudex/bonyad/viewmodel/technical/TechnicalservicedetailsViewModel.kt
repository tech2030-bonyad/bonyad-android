package fudex.bonyad.viewmodel.technical

import android.os.StrictMode
import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson
import fudex.bonyad.Model.Certificate
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Model.ServicesdetailsModel

import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.ui.Activity.technical.TechnicalservicedetailsActivity
import fudex.bonyad.ui.Adapter.technical.Serviceimageadapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class TechnicalservicedetailsViewModel(var catogaryFragment: TechnicalservicedetailsActivity) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var isshow: ObservableBoolean = ObservableBoolean(false)
    var context: TechnicalservicedetailsActivity = TechnicalservicedetailsActivity()
    private var mLoading = false
    var servicedata = ObservableField<ServicesdetailsModel>()
    var services: ArrayList<Certificate> = ArrayList()
    var img = ObservableField<String>("")
    var userimg = ObservableField<String>("")
    var statusTxt = ObservableField<String>("")
    private val orderssadapter = Serviceimageadapter()
    var copystring = ""

    init {
        this.context = catogaryFragment
        getservicedetails()
        var linearlayout = LinearLayoutManager(context)
        linearlayout!!.orientation = LinearLayoutManager.HORIZONTAL
        context.binding.images.layoutManager = linearlayout
        context.binding.images.adapter = orderssadapter
        context.binding.images.overScrollMode = View.OVER_SCROLL_NEVER
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(context.binding.images)
    }


    fun getservicedetails() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java
        )


        val call: Call<ServicesdetailsModel?>? =
            apiService.getservicesdetails(context.intent.getIntExtra("id", 0))
        call?.enqueue(object : Callback<ServicesdetailsModel?> {
            override fun onResponse(
                call: Call<ServicesdetailsModel?>,
                response: Response<ServicesdetailsModel?>
            ) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    servicedata.set(data!!)
                    services.clear()
                    services.addAll(data?.data?.images!!)
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
                                getservicedetails()
                            }
                        })
                }

                isloading.set(false)
            }

            override fun onFailure(call: Call<ServicesdetailsModel?>, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection), context)
                isloading.set(false)

            }
        })
    }

    fun back() {
        context.onBackPressed()
    }

}