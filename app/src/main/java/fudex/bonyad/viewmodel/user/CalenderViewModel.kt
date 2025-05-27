package fudex.bonyad.viewmodel.user

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import fudex.bonyad.Model.AddressesDatum

import fudex.bonyad.ui.Fragment.user.CalenderdialogFragment
import org.joda.time.format.DateTimeFormat
import java.util.Locale


class CalenderViewModel(var catogaryFragment: CalenderdialogFragment) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var addressList: ArrayList<AddressesDatum> = ArrayList<AddressesDatum>()
    var context: CalenderdialogFragment = CalenderdialogFragment()
    var date = ""
    init {
        this.context = catogaryFragment
        context.binding.calender.minDate = System.currentTimeMillis()
        context.binding.calender.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // Note: 'month' is 0-based (January = 0)
            val localDate = org.joda.time.LocalDate(year, month + 1, dayOfMonth)
            Log.d("SelectedDate", date)
            val formatter = DateTimeFormat.forPattern("yyyy-MM-dd").withLocale(Locale.ENGLISH)
            date = formatter.print(localDate)
        }
    }


    fun confirm() {
        context.dialogListener?.onDataReceived("calender" + date )
        context.dismiss()
    }
    fun back(){
        context.dismiss()
    }


}