package fudex.bonyad.viewmodel.technical

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.LinearLayoutManager
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson

import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Data.Userdata
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.Helper.Utilities.isOverlapping
import fudex.bonyad.Model.Availability
import fudex.bonyad.Model.Availabilityadd
import fudex.bonyad.Model.Dateadd
import fudex.bonyad.Model.DayAvailability
import fudex.bonyad.Model.ScheduleResponse

import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.ui.Activity.technical.Appointmentmanagectivity

import fudex.bonyad.Model.DistanceModel
import fudex.bonyad.Model.LoginData
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Activity.ActiveuserActivity
import fudex.bonyad.ui.Activity.technical.TechnicalHomeActivity
import fudex.bonyad.ui.Activity.user.UserhomeActivity
import fudex.bonyad.ui.Adapter.technical.Avalibiltyadapter
import fudex.bonyad.ui.Adapter.technical.Daysadapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import kotlin.collections.ArrayList


class AppointmentmanageViewModel(activity: Appointmentmanagectivity) : BaseObservable() {
    var activity: Appointmentmanagectivity = Appointmentmanagectivity()

    var isloading: ObservableBoolean = ObservableBoolean(false)
    var isenable: ObservableBoolean = ObservableBoolean(false)
    var dayname = ObservableField<String>("")
    var dayslist : ArrayList<DistanceModel> = ArrayList()
    var availabilityList: ArrayList<DayAvailability> = ArrayList()
    var timeList: ArrayList<Availability> = ArrayList()
    var day = 6
    private val daysadapter = Daysadapter()
    private val avalibiltyadapter = Avalibiltyadapter()

    init {
        this.activity = activity
        var linearlayout = LinearLayoutManager(activity)
        linearlayout!!.orientation = LinearLayoutManager.HORIZONTAL
        activity.binding.daysList.layoutManager = linearlayout
        activity.binding.daysList.adapter = daysadapter
        var linearlayout1 = LinearLayoutManager(activity)
        linearlayout1!!.orientation = LinearLayoutManager.VERTICAL
        activity.binding.appointment.layoutManager = linearlayout1
        activity.binding.appointment.adapter = avalibiltyadapter
        dayslist.add(DistanceModel(6, activity.getString(R.string.saturday)))
        dayslist.add(DistanceModel(0, activity.getString(R.string.sunday)))
        dayslist.add(DistanceModel(1, activity.getString(R.string.monday)))
        dayslist.add(DistanceModel(2, activity.getString(R.string.tuesday)))
        dayslist.add(DistanceModel(3, activity.getString(R.string.wednesday)))
        dayslist.add(DistanceModel(4, activity.getString(R.string.thursday)))
        dayslist.add(DistanceModel(5, activity.getString(R.string.friday)))
        getavailbilty()
    }
    fun back(){
        activity.onBackPressed()
    }
    fun getavailbilty() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)
        var call: Call<ScheduleResponse?>? = null
        call = apiService.getavailbilty()
        call?.enqueue(object : Callback<ScheduleResponse?> {
            override fun onResponse(call: Call<ScheduleResponse?>, response: Response<ScheduleResponse?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    availabilityList.clear()
                    availabilityList.addAll(data?.data!!)
                    for (index in availabilityList){
                        if (index.day_of_week == day){
                            timeList.addAll(index.availabilities!!)
                            break
                        }
                    }
                    selectdate(day)
                    notifyChange()
                }else {
                    val errorText = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            getavailbilty()
                        }
                    })
                }

                isloading.set(false)
            }

            override fun onFailure(call: Call<ScheduleResponse?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                isloading.set(false)
            }
        })
    }
    fun selectdate(day : Int){
        timeList.clear()
        this.day = day
        for (item in dayslist){
            if (item.id == day){
                dayname.set(item.title!!)
            }
        }
        for (index in availabilityList){
            if (index.day_of_week == day){
                timeList.addAll(index.availabilities!!)
                notifyChange()
                break
            }
        }
        notifyChange()
    }
    fun addappointment(){
        var dayname = ""
        for (index in dayslist){
            if (index.id == day){
                dayname = index.title!!
                break
            }
        }
        var isgood = false
        for (index in availabilityList){
            if (index.day_of_week == day){
                isgood = true
                var availability = Availability(index.availabilities?.size!! + 1,day,index.day_name,"","",true)
                index.availabilities?.add(availability)
                selectdate(day)
                break
            }
        }
        if (isgood == false){
            var availability = Availability(1,day,dayname,"","",true)
            var availabilitylist : ArrayList<Availability> = ArrayList()
            availabilitylist.add(availability)
            var days = DayAvailability(day,dayname,availabilitylist)
            this.availabilityList.add(days)
            selectdate(day)
        }
    }
    fun changetime(type:String,id:Int,time:String){
        var index1 = 0
        for (index in availabilityList) {
            index1 = 0
            for (item in index.availabilities!!) {
                if (id == item.id!!) {
                    break
                }
                index1 = index1 + 1
            }
        }
        Utilities.showTimePickerDialog(activity, if (time == ""){"00:00"}else{time}) { selectedTime ->
            for (index in availabilityList){
                if (index.day_of_week!! == day){
                    val isConflict = isOverlapping(selectedTime, index.availabilities!!,index1,type)
                    if (isConflict == true){
                        Dialogs.showToast(activity.getString(R.string.added_before),activity)
                    }else {
                        for (item in index.availabilities!!){
                            if (id == item.id!!){
                                if (type == "from"){
                                    item.start_time = selectedTime
                                }else {
                                    item.end_time = selectedTime
                                }
                                notifyChange()
                            }
                        }
                    }
                }
            }
        }
        selectdate(day)
    }
    fun delete(id : Int){
        for (index in availabilityList){
            if (day == index.day_of_week!!){
                var index1 = 0
                for (item in index.availabilities!!){
                    if (item.id == id){
                        index.availabilities.removeAt(index1)
                        break
                    }
                    index1 = index1 + 1
                }
            }
        }
        selectdate(day)
    }
    fun validate(){
        var error = ""
        for (index in availabilityList){
            for (item in index.availabilities!!){
                if (item.start_time == "" || item.end_time == ""){
                    error = error + activity.getString(R.string.there_is_time_not_added_in_day) + " " + index.day_name!! +"\n"
                }
            }
        }
        if (error == ""){
            var days :ArrayList<Availabilityadd> = ArrayList()
            for (index in availabilityList){
                for (item in index.availabilities!!){
                   days.add(Availabilityadd(index.day_of_week!!,item.start_time!!,item.end_time!!))
                }
            }
            var dates = Dateadd(days)
            adddates(dates)
        }else {
            Dialogs.showToast(error,activity)
        }
    }
    fun adddates(dates:Dateadd){
        Utilities.disabletouch(activity)
        isenable.set(false)
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)
        val call: Call<ErrorResponse?>? = apiService.adddates(dates)
        call?.enqueue(object : Callback<ErrorResponse?> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<ErrorResponse?>, response: Response<ErrorResponse?>) {
                if (response.code() == 200 || response.code() == 201) {
                   Dialogs.showToast(response.body()!!.message!!,activity)
                    back()
                } else{
                    val errorText = response.errorBody()?.string() ?: "{}"
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            adddates(dates)
                        }
                    })
                }
                isloading.set(false)
                isenable.set(true)
                Utilities.enabletouch(activity)

            }

            override fun onFailure(call: Call<ErrorResponse?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                isloading.set(false)
                isenable.set(true)
                Utilities.enabletouch(activity)
            }
        })
    }
}
