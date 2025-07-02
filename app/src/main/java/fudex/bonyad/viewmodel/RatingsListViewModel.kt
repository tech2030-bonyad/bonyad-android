package fudex.bonyad.viewmodel

import android.graphics.Canvas
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.Model.NotsModel
import fudex.bonyad.Model.RatingDatum
import fudex.bonyad.Model.RatingModel
import fudex.bonyad.Model.RatingsModel
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.ui.Activity.RatingActivity
import fudex.bonyad.ui.Adapter.Ratingadapter
import fudex.bonyad.ui.Fragment.DeletetFragment
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RatingsListViewModel(var catogaryFragment: RatingActivity) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var ratingList: ArrayList<RatingDatum> = ArrayList()
    var context: RatingActivity = RatingActivity()
    var linearlayout: LinearLayoutManager? = null
    var star5 = ObservableField<Int>(0)
    var star4 = ObservableField<Int>(0)
    var star3 = ObservableField<Int>(0)
    var star2 = ObservableField<Int>(0)
    var star1 = ObservableField<Int>(0)
    var count = ObservableField<Int>(0)
    var rate = ObservableField<String>("")

    private val ratingadapter = Ratingadapter()
    var page = 1
    private var mLoading = false
    var notId = ""
    init {
        this.context = catogaryFragment
        linearlayout = LinearLayoutManager(context)
        linearlayout!!.orientation = LinearLayoutManager.VERTICAL
        context.binding.commentList.layoutManager = linearlayout
        context.binding.commentList.adapter = ratingadapter
        getratings()
        scroll()
        swiptorefresh()
    }

    fun getratings() {
        context.binding.commentList.showShimmer()
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java)
        val call: Call<RatingsModel?>? = apiService.getratings(context.intent.getStringExtra("type"),context.intent.getIntExtra("id",0),page,10)

        call?.enqueue(object : Callback<RatingsModel?> {
            override fun onResponse(call: Call<RatingsModel?>, response: Response<RatingsModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    if (page == 1) {
                        ratingList.clear()
                        star5.set(data?.star_ratings?.star5?.ratio ?: 0)
                        star4.set(data?.star_ratings?.star4?.ratio ?: 0)
                        star3.set(data?.star_ratings?.star3?.ratio ?: 0)
                        star2.set(data?.star_ratings?.star2?.ratio ?: 0)
                        star1.set(data?.star_ratings?.star1?.ratio ?: 0)
                        count.set(data?.total_count ?: 0)
                        rate.set(data?.average_rating ?: "")
                    }
                    ratingList.addAll(data?.data!!)
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
                            getratings()
                        }
                    })
                }

                isloading.set(false)
                context.binding.commentList.hideShimmer()
            }

            override fun onFailure(call: Call<RatingsModel?>, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection) , context)
                isloading.set(false)
                context.binding.commentList.hideShimmer()

            }
        })
    }
    fun back(){
        context.onBackPressed()
    }
    fun scroll() {
        context.binding.scroll.setOnScrollChangeListener { _, _, scrollY, _, _ ->
            if (scrollY == context.binding.scroll.getChildAt(0).measuredHeight - context.binding.scroll.height) {
                if (!mLoading) {
                    mLoading = true
                    getratings()
                }
            }
        }

    }

    fun swiptorefresh() {
//        context.binding.swip.setOnRefreshListener {
//            Handler().postDelayed(Runnable {
//                context.binding.swip.isRefreshing = false
//            }, 1500)
//            page = 1
//            getratings()
//        }
    }

}