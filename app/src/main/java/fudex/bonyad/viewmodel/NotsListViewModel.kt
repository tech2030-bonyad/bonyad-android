package fudex.bonyad.viewmodel

import android.content.Intent
import android.graphics.Canvas
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.Model.NotDatum
import fudex.bonyad.Model.NotsModel
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.ui.Activity.NotificationsActivity
import fudex.bonyad.ui.Activity.user.AddadressActivity
import fudex.bonyad.ui.Adapter.Notsadapter
import fudex.bonyad.ui.Fragment.DeletetFragment
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NotsListViewModel(var catogaryFragment: NotificationsActivity) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var notsList: ArrayList<NotDatum> = ArrayList()
    var context: NotificationsActivity = NotificationsActivity()
    var linearlayout: LinearLayoutManager? = null
    private val notsadapter = Notsadapter()
    var page = 1
    private var mLoading = false
    var notId = ""
    init {
        this.context = catogaryFragment
        linearlayout = LinearLayoutManager(context)
        linearlayout!!.orientation = LinearLayoutManager.VERTICAL
        context.binding.notsList.layoutManager = linearlayout
        context.binding.notsList.adapter = notsadapter
        getnots()
        scroll()
        swiptorefresh()
        swip()
    }

    fun getnots() {
        context.binding.notsList.showShimmer()
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java)
        val call: Call<NotsModel?>? = apiService.getnots(page,10)

        call?.enqueue(object : Callback<NotsModel?> {
            override fun onResponse(call: Call<NotsModel?>, response: Response<NotsModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    if (page == 1) {
                        notsList.clear()
                    }
                    notsList.addAll(data?.data!!)
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
                            getnots()
                        }
                    })
                }

                isloading.set(false)
                context.binding.notsList.hideShimmer()
            }

            override fun onFailure(call: Call<NotsModel?>, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection) , context)
                isloading.set(false)
                context.binding.notsList.hideShimmer()

            }
        })
    }
    fun back(){
        context.onBackPressed()
    }
    fun scroll() {
        context.binding.notsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount: Int = linearlayout!!.getItemCount()
                val visibleItemCount: Int = linearlayout!!.findLastVisibleItemPosition()
                Log.e("pos", visibleItemCount.toString())
                if (!mLoading && visibleItemCount >= totalItemCount - 3 && page > 1) {
                    mLoading = true
                    getnots()
                }

                super.onScrolled(recyclerView, dx, dy)
            }


        })

    }
    fun delete(){
        var fragment = DeletetFragment()
        var bundle = Bundle()
        bundle.putString("address","")
        fragment.arguments = bundle
        fragment.show(context.supportFragmentManager , "logout")
    }
    fun deletenots(){
        Utilities.disabletouch(context)
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java)
        val call: Call<ErrorResponse?>? = apiService.deletenots(notId)
        call?.enqueue(object : Callback<ErrorResponse?> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<ErrorResponse?>, response: Response<ErrorResponse?>) {
                if (response.code() == 200 || response.code() == 201) {
                   for (item in notsList){
                       if (item.id == notId){
                           notsList.remove(item)
                           notifyChange()
                           break
                       }
                   }
                }else {
                    val errorText = response.errorBody()?.string()
                    Log.e("data",errorText!!)
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(context, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            deletenots()
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
    fun setdefult(){
//        Utilities.disabletouch(context)
//        isloading.set(true)
//        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
//            ApiInterface::class.java)
//        var requestBody: RequestBody? = null
//        requestBody =  MultipartBody.Builder()
//            .setType(MultipartBody.FORM)
//            .addFormDataPart("is_default", "1")
//            .build()
//        val call: Call<ErrorResponse?>? = apiService.setdefualt(addressId,requestBody)
//        call?.enqueue(object : Callback<ErrorResponse?> {
//            @RequiresApi(Build.VERSION_CODES.O)
//            override fun onResponse(call: Call<ErrorResponse?>, response: Response<ErrorResponse?>) {
//                if (response.code() == 200 || response.code() == 201) {
//                    for (index in addressList){
//                        if(index.is_default == 1 && index.id != addressId){
//                            index.is_default = 0
//                        }
//                        if (index.id == addressId){
//                            index.is_default = 1
//                        }
//                    }
//                    notifyChange()
//                }else {
//                    val errorText = response.errorBody()?.string()
//                    Log.e("data",errorText!!)
//                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
//                    APIModel.handleFailure1(context, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
//                        override fun onRefresh() {
//                            deleteaddress()
//                        }
//                    })
//                }
//                isloading.set(false)
//                Utilities.enabletouch(context)
//
//            }
//
//            override fun onFailure(call: Call<ErrorResponse?>, t: Throwable) {
//                Dialogs.showToast(context.getString(R.string.check_your_connection) , context)
//                isloading.set(false)
//                Utilities.enabletouch(context)
//            }
//        })
    }
    fun swiptorefresh() {
        context.binding.swip.setOnRefreshListener {
            Handler().postDelayed(Runnable {
                context.binding.swip.isRefreshing = false
            }, 1500)
            page = 1
            getnots()
        }
    }
    fun swip(){
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        notId = notsList.get(position).id ?: ""
                        deletenots()
                    }
                    ItemTouchHelper.RIGHT -> {
                        notId = notsList.get(position).id ?: ""
                        deletenots()
                    }
                }
            }
            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                RecyclerViewSwipeDecorator.Builder(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
                    .addSwipeLeftActionIcon(R.drawable.delete22) // Your delete icon
                    .addSwipeRightBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
                    .addSwipeRightActionIcon(R.drawable.delete22) // Your archive icon
                    .create()
                    .decorate()

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        })
        itemTouchHelper.attachToRecyclerView(context.binding.notsList)

    }

}