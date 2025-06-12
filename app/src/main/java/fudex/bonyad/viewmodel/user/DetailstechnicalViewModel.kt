package fudex.bonyad.viewmodel.user


import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Data.Craetereserve
import fudex.bonyad.Helper.Camera.Companion.activity
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.Model.Availability
import fudex.bonyad.Model.CalendarDay
import fudex.bonyad.Model.DetailstechnicalModel
import fudex.bonyad.Model.ScheduleResponse
import fudex.bonyad.Model.Service
import fudex.bonyad.Model.StatesDatum
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.ui.Activity.user.DetailsspeciallistActivity
import fudex.bonyad.ui.Activity.user.LocationmapActivity
import fudex.bonyad.ui.Activity.user.UserhomeActivity
import fudex.bonyad.ui.Adapter.user.CalendarAdapter
import fudex.bonyad.ui.Adapter.user.Servicesdetailsadapter
import fudex.bonyad.ui.Adapter.user.Timesadapter
import fudex.bonyad.ui.Fragment.user.CalenderdialogFragment
import fudex.bonyad.ui.Fragment.user.SpeciallistreservedoneFragment
import org.joda.time.LocalDate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.format.DateTimeFormatter


class DetailstechnicalViewModel(var catogaryFragment: DetailsspeciallistActivity) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var context: DetailsspeciallistActivity = DetailsspeciallistActivity()
    var img = ObservableField<String>("")
    var addressname = ObservableField<String>("")
    var dateString = ObservableField<String>("")
    var date1 = ""
    var times: ArrayList<Availability> = ArrayList()
    var services: ArrayList<StatesDatum> = ArrayList()
    var details = ObservableField<DetailstechnicalModel>()
    var timeId = 0
    var dayId = 0
    private val timesadapter = Timesadapter()
    private val servicesdetailsadapter = Servicesdetailsadapter()
    var lat = ""
    var lng = ""
    init {
        this.context = catogaryFragment
        context.binding.dateList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        var linearlayout1 = LinearLayoutManager(activity)
        linearlayout1!!.orientation = LinearLayoutManager.HORIZONTAL
        context.binding.serviceList.layoutManager = linearlayout1
        context.binding.serviceList.adapter = servicesdetailsadapter
        context.binding.serviceList.setHasFixedSize(false)
        var linearlayout2 = GridLayoutManager(activity,2)
        context.binding.timeList.layoutManager = linearlayout2
        context.binding.timeList.adapter = timesadapter
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setcalender(LocalDate.now())
        }
        gettechnicaldetails()
    }
    fun gettechnicaldetails() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java
        )

        val call: Call<DetailstechnicalModel?>? =
            apiService.gettechnical(context.intent.getIntExtra("id", 0))
        call?.enqueue(object : Callback<DetailstechnicalModel?> {
            override fun onResponse(
                call: Call<DetailstechnicalModel?>,
                response: Response<DetailstechnicalModel?>
            ) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    details.set(data)
                    img.set(data?.data?.avatar!!)
                    services.clear()
                    services.addAll(data?.data?.services!!)
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
                                gettechnicaldetails()
                            }
                        })
                }
                isloading.set(false)
            }

            override fun onFailure(call: Call<DetailstechnicalModel?>, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection), context)
                isloading.set(false)

            }
        })
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun setcalender(today : LocalDate){
        val days = (0..29).map { offset ->
            val date = today.plusDays(offset.toLong().toInt())
            CalendarDay(
                date = date,
                isToday = date == today,
                isSelected = date.dayOfMonth == 18 // or bind from ViewModel/state
            )
        }

        val adapter = CalendarAdapter(days) { selectedDate ->
            // Handle date selection
            notifyChange()
            date1 = selectedDate.toString("yyyy-MM-dd")
            dateString.set(Utilities.formatDateToArabic(selectedDate,context.getString(R.string.lang)))
            getslots()

        }
        adapter.date = today
        dateString.set(Utilities.formatDateToArabic(today,context.getString(R.string.lang)))
        date1 = today.toString("yyyy-MM-dd")
        getslots()
        context.binding.dateList.adapter = adapter
    }

    fun back(){
        context.onBackPressed()
    }
    fun changeaddress(){
//        try {
//            if (address.get()!!.id ?: 0 == 0) {
//                var intent: Intent = Intent(context, AddaddressActivity::class.java)
//                intent.putExtra("loc","")
//                context.startActivityForResult(intent, 111)
//            }else {
//                var fragment = Address1dialogsFragment()
//                var bundle = Bundle()
//                bundle.putInt("id",address.get()!!.id!!)
//                fragment.arguments = bundle
//                fragment.show(context.supportFragmentManager , "address")
//            }
//        }catch (e:Exception){
//            var intent: Intent = Intent(context, AddaddressActivity::class.java)
//            intent.putExtra("loc","")
//            context.startActivityForResult(intent, 111)
//        }
    }

    fun calender(){
        var fragment = CalenderdialogFragment()
        fragment.show(context.supportFragmentManager , "calender")
    }
    fun validate(){
        var error = ""
        if (lat == ""){
            error = error + context.getString(R.string.add_address) + "\n"
        }
        if (timeId == 0){
            error = error + context.getString(R.string.select) + " " + context.getString(R.string.time) + "\n"
        }

        if (error == ""){
            createorder()
        }else {
            Dialogs.showToast(error,context)
        }
    }
    fun getslots() {
        timeId = 0
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(ApiInterface::class.java)
        val serviceIds: ArrayList<Int> = ArrayList()
        for (index in services){
            serviceIds.add(index.id!!)
        }
        val call: Call<ScheduleResponse?>? = apiService.getSlots(context.intent.getIntExtra("id",0),date1)
        call?.enqueue(object : Callback<ScheduleResponse?> {
            override fun onResponse(call: Call<ScheduleResponse?>, response: Response<ScheduleResponse?>) {
                if (response!!.code() == 200 || response!!.code() == 201) {
                    var data = response.body()
                    times.clear()
                    if (data?.data?.size ?: 0 > 0 ){
                        times.addAll(data?.data?.get(0)!!.availabilities!!)
                    }
                    notifyChange()
                }else {
                    val errorText = response.errorBody()?.string() ?: "{}"
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(context, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            getslots()
                        }
                    })
                }
                isloading.set(false)

            }

            override fun onFailure(call: Call<ScheduleResponse?>?, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection) , context)
                isloading.set(false)
            }
        })
    }
    fun createorder() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(ApiInterface::class.java)
        var createorder = Craetereserve(context.intent.getIntExtra("id", 0),dayId,date1,timeId,lat,lng)
        val call: Call<ErrorResponse?>? = if (context.intent.hasExtra("reservaionid")){apiService.editreserve(context.intent.getIntExtra("reservaionid", 0),createorder)}else{apiService.createreserve(createorder)}
        call?.enqueue(object : Callback<ErrorResponse?> {
            override fun onResponse(call: Call<ErrorResponse?>, response: Response<ErrorResponse?>) {
                if (response!!.code() == 200 || response!!.code() == 201) {
                    var fragment = SpeciallistreservedoneFragment()
                    fragment.show(context.supportFragmentManager , "promo")
                }else {
                    val errorText = response.errorBody()?.string() ?: "{}"
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(context, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            getslots()
                        }
                    })
                }
                isloading.set(false)

            }

            override fun onFailure(call: Call<ErrorResponse?>?, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection) , context)
                isloading.set(false)
            }
        })

    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDate(input: String): String {
        val inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        val date = java.time.LocalDate.parse(input, inputFormatter)
        return date.format(outputFormatter)
    }
    fun addaddress(){
        val i = Intent(context, LocationmapActivity::class.java)
        if (lat == "") {
            i.putExtra("lat" , 0.0)
            i.putExtra("lng" , 0.0)
        }else {
            i.putExtra("lat" , lat.toDouble())
            i.putExtra("lng" , lng.toDouble())
        }
        context.startActivityForResult(i, 111)
    }
}