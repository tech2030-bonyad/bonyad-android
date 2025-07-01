package fudex.bonyad.viewmodel.user

import android.app.Activity
import android.content.Intent
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.LinearLayoutManager
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson

import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Model.Availability
import fudex.bonyad.Model.DayAvailability
import fudex.bonyad.Model.HomeModel
import fudex.bonyad.Model.NotsModel
import fudex.bonyad.Model.ScheduleResponse
import fudex.bonyad.Model.Slider

import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R

import fudex.bonyad.Model.Technician
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Activity.NotificationsActivity
import fudex.bonyad.ui.Activity.user.DetailsspeciallistActivity
import fudex.bonyad.ui.Activity.user.SpecialistsActivity
import fudex.bonyad.ui.Adapter.technical.Avalibiltyadapter
import fudex.bonyad.ui.Adapter.technical.Daysadapter
import fudex.bonyad.ui.Adapter.user.Sliderhomedadapter
import fudex.bonyad.ui.Adapter.user.Technicaladapter
import fudex.bonyad.ui.Fragment.user.UserhomeFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import kotlin.collections.ArrayList


class UserhomefragmentViewModel(context: UserhomeFragment) : BaseObservable() {
    var context: UserhomeFragment = UserhomeFragment()
    var activity = Activity()
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var isenable: ObservableBoolean = ObservableBoolean(false)
    var username = ObservableField<String>("")
    var technicallist : ArrayList<Technician> = ArrayList()
    var sliderlist : ArrayList<Slider> = ArrayList()
    var day = 6
    private val technicaladapter = Technicaladapter()
    var handler: Handler = Handler()
    lateinit var pagerAdapter: Sliderhomedadapter
    var count = ObservableField<Int>(0)

    init {
        this.context = context
        activity = context.requireActivity()
        var linearlayout = LinearLayoutManager(activity)
        linearlayout!!.orientation = LinearLayoutManager.VERTICAL
        context.binding.specialList.layoutManager = linearlayout
        context.binding.specialList.adapter = technicaladapter
        pagerAdapter = Sliderhomedadapter(activity!!,sliderlist)
        context.binding.slider.setAdapter(pagerAdapter)
        context.binding.indicator.setViewPager(context.binding.slider)
        pagerAdapter.registerDataSetObserver(context.binding.indicator.getDataSetObserver())
        gethome()
        if (context.getString(R.string.lang) == "ar") {
            context.binding.slider.rotationY = 180f
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
                    gethome()
                }, 1000)
            }
        })

    }
    fun getnotscount() {
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)

        val call: Call<NotsModel?>? = apiService.getnotscounts()
        call?.enqueue(object : Callback<NotsModel?> {
            override fun onResponse(call: Call<NotsModel?>, response: Response<NotsModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    count.set(data?.count ?: 0)
                    notifyChange()
                }else {
                    val errorText = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            getnotscount()
                        }
                    })
                }

            }

            override fun onFailure(call: Call<NotsModel?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
            }
        })
    }
    fun back(){
        activity.onBackPressed()
    }
    fun gethome() {
        context.binding.specialList.showShimmer()
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)
        var call: Call<HomeModel?>? = null
        call = apiService.gethome(if (context.binding.searchTxt.text.toString() == ""){null}else{context.binding.searchTxt.text.toString()})
        call?.enqueue(object : Callback<HomeModel?> {
            override fun onResponse(call: Call<HomeModel?>, response: Response<HomeModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    sliderlist.clear()
                    technicallist.clear()
                    technicallist.addAll(data?.data!!.technicians!!)
                    sliderlist.addAll(data?.data!!.sliders!!)
                    pagerAdapter.notifyDataSetChanged()
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
                context.binding.specialList.hideShimmer()
                isloading.set(false)
            }

            override fun onFailure(call: Call<HomeModel?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                isloading.set(false)
                context.binding.specialList.hideShimmer()
            }
        })
    }
    fun moretechnical(){
        var intent: Intent = Intent(context?.requireActivity(), SpecialistsActivity::class.java)
        context?.startActivity(intent)
    }
    fun not(){
        var intent: Intent = Intent(context?.requireActivity(), NotificationsActivity::class.java)
        context?.startActivity(intent)
    }
    fun filter(){

    }
}
