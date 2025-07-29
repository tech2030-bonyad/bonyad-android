package fudex.bonyad.viewmodel.merchant

import android.app.Activity
import android.app.DatePickerDialog
import android.widget.PopupMenu
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.LinearLayoutManager
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.Model.FilterModel
import fudex.bonyad.Model.FilterorderModel
import fudex.bonyad.Model.StatesDatum
import fudex.bonyad.Model.StatesModel
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.ui.Adapter.user.Technicafilterladapter
import fudex.bonyad.ui.Fragment.merchant.FilterordersFragment
import fudex.bonyad.ui.Fragment.user.FilterspecialFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class FilterorderViewModel(var catogaryFragment: FilterordersFragment) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var ishiden: ObservableBoolean = ObservableBoolean(false)
    var context: FilterordersFragment = FilterordersFragment()
    var activity = Activity()
    val statusname = ObservableField<String>("")
    var status = 0
    var statusList: ArrayList<StatesDatum> = ArrayList()
    var servicesList: ArrayList<StatesDatum> = ArrayList()
    var from = ""
    var to = ""
    private var month = "-1"

    init {
        this.context = catogaryFragment
        activity = context.requireActivity()
        val filter = Gson().fromJson(context.requireArguments().getString("filter","{}"), FilterorderModel::class.java)
        if (context.requireArguments().getInt("status") == 1){
            ishiden.set(false)
        }else {
            ishiden.set(true)
        }
        status = filter.status ?: 0
        from = filter.from ?: ""
        to = filter.to ?: ""
        if (status == 0) {
            statusname.set(context.getString(R.string.all_orders))
        }else if (status == 1) {
            statusname.set(context.getString(R.string.new_order))
        }else if (status == 7) {
            statusname.set(context.getString(R.string.in_progress))
        }else if (status == 8) {
            statusname.set(context.getString(R.string.in_shipping))
        }
        statusList.clear()
        statusList.add(StatesDatum(0,context.getString(R.string.all_orders)))
        statusList.add(StatesDatum(1, context.getString(R.string.new_order)))
        statusList.add(StatesDatum(7, context.getString(R.string.in_progress)))
        statusList.add(StatesDatum(8, context.getString(R.string.in_shipping)))
        notifyChange()
    }


    fun statusclick() {
        var secPopUp = PopupMenu(activity, context.binding.cityLin)
        for (i in statusList.indices) {
            secPopUp?.menu?.add(i, i, i, statusList[i].name)

        }
        secPopUp?.setOnMenuItemClickListener { item ->
            statusname.set(statusList[item.itemId].name!!)
            status = statusList[item.itemId].id!!
            false
        }
        secPopUp.show()
    }
    fun clearall(){
        from = ""
        to = ""
        status = 0
        statusname.set(context.getString(R.string.all_orders))
        notifyChange()
    }
    fun save(){
        var filter = FilterorderModel(status,from,to)
        context.dialogListener?.onDataReceived("filter" + Gson().toJson(filter))
        context.dismiss()
    }
    fun clickfromdate(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(activity, { _, mYear, monthOfYear, dayOfMonth ->
            this.month = (monthOfYear + 1).toString()
            if (this.month.length == 1) {
                this.month = "0${monthOfYear + 1}"
            }
            from = (("$mYear-${this.month}-${
                if (dayOfMonth < 10) {
                    "0$dayOfMonth"
                } else
                    dayOfMonth
            }"))
            notifyChange()
        }, year, month, day)
        dpd.datePicker.maxDate = System.currentTimeMillis()
        dpd.show()
    }
    fun clicktodate(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(activity, { _, mYear, monthOfYear, dayOfMonth ->
            this.month = (monthOfYear + 1).toString()
            if (this.month.length == 1) {
                this.month = "0${monthOfYear + 1}"
            }
            to = (("$mYear-${this.month}-${
                if (dayOfMonth < 10) {
                    "0$dayOfMonth"
                } else
                    dayOfMonth
            }"))
            notifyChange()
        }, year, month, day)
        if (from != ""){
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date: Date = dateFormat.parse(from)!!
            val calendar1 = Calendar.getInstance().apply {
                time = date
            }
            dpd.datePicker.minDate = calendar1.timeInMillis
        }
        dpd.datePicker.maxDate = System.currentTimeMillis()
        dpd.show()
    }
}