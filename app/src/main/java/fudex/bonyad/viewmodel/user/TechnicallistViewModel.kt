package fudex.bonyad.viewmodel.user

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.Model.AddserviceModel
import fudex.bonyad.Model.FilterModel
import fudex.bonyad.Model.StatesModel
import fudex.bonyad.Model.TechnicalModel
import fudex.bonyad.Model.Technician
import fudex.bonyad.Model.UserModel
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.ui.Activity.LoginActivity
import fudex.bonyad.ui.Activity.user.SpecialistsActivity

import fudex.bonyad.ui.Adapter.user.Technical1adapter
import fudex.bonyad.ui.Fragment.DeletetFragment
import fudex.bonyad.ui.Fragment.user.FilterspecialFragment

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TechnicallistViewModel(var catogaryFragment: SpecialistsActivity) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var context: SpecialistsActivity = SpecialistsActivity()
    var img = ObservableField<String>("")
    var islogin = ObservableField<Boolean>(false)
    var userdata = ObservableField<UserModel>()
    var technicalList: ArrayList<Technician> = ArrayList()
    var  linearlayout: LinearLayoutManager? = null
    private val technicaladapter = Technical1adapter()
    var handler: Handler = Handler()
    var page = 1
    private var mLoading = false
    var serviceId = 0
    var zoneId = 0
    var filter = FilterModel(0,0)

    init {
        this.context = catogaryFragment
        linearlayout = LinearLayoutManager(context)
        linearlayout!!.orientation = LinearLayoutManager.VERTICAL
        context.binding.specialList.layoutManager = linearlayout
        context.binding.specialList.adapter = technicaladapter
        if (context.getString(R.string.lang) == "ar"){
            context.binding.searchTxt.gravity = Gravity.RIGHT
        }
        gettechnicals()
        scroll()
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
                    gettechnicals()
                }, 1000)
            }
        })
    }
    fun back(){
        context.onBackPressed()
    }
    fun gettechnicals() {
        if (page == 1) {
            context.binding.specialList.showShimmer()
        }
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java)
        var call: Call<TechnicalModel?>? = null
        var zones: ArrayList<Int>  = ArrayList()
        var services: ArrayList<Int>  = ArrayList()
        if (zoneId != 0 ){
            zones.add(zoneId)
        }
        if (serviceId != 0 ){
            services.add(serviceId)
        }
        call = apiService.gettechnicals(page,10,if (zones.size == 0){null}else{zones},if (services.size == 0){null}else{services},if (context.binding.searchTxt.text.toString() == ""){null}else{context.binding.searchTxt.text.toString()})
        call?.enqueue(object : Callback<TechnicalModel?> {
            override fun onResponse(call: Call<TechnicalModel?>, response: Response<TechnicalModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    if (page == 1) {
                        technicalList.clear()
                    }
                    technicalList.addAll(data?.data!!)
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
                            gettechnicals()
                        }
                    })
                }
                context.binding.specialList.hideShimmer()
                isloading.set(false)
            }

            override fun onFailure(call: Call<TechnicalModel?>, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection) , context)
                isloading.set(false)
                context.binding.specialList.hideShimmer()
            }
        })
    }
    fun scroll() {
        context.binding.specialList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount: Int = linearlayout!!.getItemCount()
                val visibleItemCount: Int = linearlayout!!.findLastVisibleItemPosition()
                Log.e("pos", visibleItemCount.toString())
                if (!mLoading && visibleItemCount >= totalItemCount - 3 && page > 1) {
                    mLoading = true
                    gettechnicals()
                }

                super.onScrolled(recyclerView, dx, dy)
            }


        })

    }
    fun filter(){
        var fragment = FilterspecialFragment()
        var bundle = Bundle()
        bundle.putString("filter",Gson().toJson(filter))
        fragment.arguments = bundle
        fragment.show(context.supportFragmentManager , "filter")
    }
}