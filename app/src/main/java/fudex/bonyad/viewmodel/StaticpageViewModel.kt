package fudex.bonyad.viewmodel


import android.text.Html
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.ui.Activity.StaticpageActivity
import onnetysolutions.sadded.Model.AboutModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StaticpageViewModel(var catogaryFragment: StaticpageActivity) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var context: StaticpageActivity = StaticpageActivity()
    val txt = ObservableField<String>("")
    init {
        this.context = catogaryFragment
        getabout()
        if (context.intent.getIntExtra("type",0) == 1){
           context.binding.title.setText(R.string.about_the_platform)
        }else  if (context.intent.getIntExtra("type",0) == 2){
            context.binding.title.setText(R.string.terms_and_conditions)
        }else if (context.intent.getIntExtra("type",0) == 3){
            context.binding.title.setText(R.string.privacy)
        }
    }


    fun getabout() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(ApiInterface::class.java)
        val call: Call<AboutModel?>? = if (context.intent.getIntExtra("type",0) == 1){apiService.aboutus()}else if (context.intent.getIntExtra("type",0) == 2){apiService.terms()}else{apiService.privacy()}
        call?.enqueue(object : Callback<AboutModel?> {
            override fun onResponse(call: Call<AboutModel?>, response: Response<AboutModel?>) {
                if (response!!.code() == 200 || response!!.code() == 201) {
                    var data = response.body()
                    txt.set(Html.fromHtml(data?.data?.description!!).toString())
                }else {
                    val errorText = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(context, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            getabout()
                        }
                    })
            }
                isloading.set(false)

            }

            override fun onFailure(call: Call<AboutModel?>?, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection) , context)
                isloading.set(false)
            }
        })
    }
    fun back(){
        context.onBackPressed()
    }
}