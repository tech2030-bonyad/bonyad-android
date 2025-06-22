package fudex.bonyad.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import android.view.Gravity
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.adapters.TextViewBindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Data.Chatdata
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.Model.ChatModel
import fudex.bonyad.Model.MessageItem
import fudex.bonyad.Model.UserInfo
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Activity.ChatActivity
import fudex.bonyad.ui.Adapter.Chatadapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChatListViewModel(var catogaryFragment: ChatActivity) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    val messageObserv = ObservableField<String>()
    var status  = ObservableField<Int>(1)
    var img = ObservableField<String>("")
    var name = ObservableField<String>("")
    var chatList: ArrayList<MessageItem> = ArrayList()
    var activity: ChatActivity = ChatActivity()
    var linearlayout: LinearLayoutManager? = null
    private val chatadapter = Chatadapter()
    var page = 1
    private var mLoading = false
    var addressId = 0
    var userId = 0
    
    @SuppressLint("RestrictedApi")
    fun onmessageChanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            messageObserv.set(s.toString())
        }
    }
    init {
        this.activity = catogaryFragment
        linearlayout = LinearLayoutManager(activity)
        linearlayout!!.orientation = LinearLayoutManager.VERTICAL
        activity.binding.chatList.layoutManager = linearlayout
        activity.binding.chatList.adapter = chatadapter
        userId = activity.intent.getIntExtra("id",0)
        img.set(activity.intent.getStringExtra("img"))
        name.set(activity.intent.getStringExtra("name"))
       scroll()
        if (activity.getString(R.string.lang) == "ar") {
            activity.binding.message.gravity = Gravity.RIGHT
        }
       // swiptorefresh()
    }
    fun validateInput() {
        var error = false
        if (messageObserv.get() == null || messageObserv.get()!!.isEmpty()) {
            error = true
            activity.binding.message.setError(activity.getString(R.string.required))
        }
        if (!error) {
            Utilities.closeKeyboard(activity)
            sendchat()
        }
    }

    fun getchats() {
        activity.binding.chatList.showShimmer()
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)
        val call: Call<ChatModel?>? = apiService.getchat(userId,page,10)

        call?.enqueue(object : Callback<ChatModel?> {
            override fun onResponse(call: Call<ChatModel?>, response: Response<ChatModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    if (page == 1) {
                        chatList.clear()
                    }
                    chatList.addAll(data?.data!!)
                    if (response.body()!!.data!!.size > 0) {
                        page++
                        mLoading = false
                    }
                    notifyChange()
                }else {
                    val errorText = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            getchats()
                        }
                    })
                }

                isloading.set(false)
                activity.binding.chatList.hideShimmer()
            }

            override fun onFailure(call: Call<ChatModel?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                isloading.set(false)
                activity.binding.chatList.hideShimmer()

            }
        })
    }

    fun scroll() {
        activity.binding.chatList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount: Int = linearlayout!!.getItemCount()
                val visibleItemCount: Int = linearlayout!!.findLastVisibleItemPosition()
                Log.e("pos", visibleItemCount.toString())
                if (!mLoading && visibleItemCount >= totalItemCount - 3 && page > 1) {
                    mLoading = true
                    getchats()
                }

                super.onScrolled(recyclerView, dx, dy)
            }


        })

    }
   
    fun sendchat() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java
        )
        val call: Call<ErrorResponse?>? =
            apiService.sendchat(Chatdata(userId,activity.binding.message.text.toString()))
        call?.enqueue(object : Callback<ErrorResponse?> {
            override fun onResponse(
                call: Call<ErrorResponse?>,
                response: Response<ErrorResponse?>
            ) {
                if (response.code() == 200 || response.code() == 201) {
                    chatList.add(MessageItem(0,0,activity.binding.message.text.toString(),
                        UserInfo(LoginSession.getUserData(activity).user?.id ?: 0,LoginSession.getUserData(activity).user?.name ?: "" , "" ,"" , LoginSession.getUserData(activity).user?.avatar ?: ""), UserInfo(userId,name.get() , "" ,"" , img.get()),true,false,"","" , "" , "" , ""
                    ))
                    activity.binding.message.setText("")
                    notifyChange()
                }else{
                    val errorText = response.errorBody()?.string() ?: "{}"
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            sendchat()
                        }
                    })
                }
                isloading.set(false)
                notifyChange()
            }

            override fun onFailure(call: Call<ErrorResponse?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection), activity)
                isloading.set(false)

            }
        })
    }
    fun back(){
        activity.onBackPressed()
    }



//    fun swiptorefresh() {
//        context.binding.swip.setOnRefreshListener {
//            Handler().postDelayed(Runnable {
//                context.binding.swip.isRefreshing = false
//            }, 1500)
//            page = 1
//            getaddresses()
//        }
//    }
}