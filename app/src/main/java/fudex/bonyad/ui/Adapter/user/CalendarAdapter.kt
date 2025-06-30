package fudex.bonyad.ui.Adapter.user

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar
import fudex.bonyad.R
import fudex.bonyad.Model.CalendarDay
import fudex.bonyad.ui.Activity.user.DetailsspeciallistActivity
import org.joda.time.LocalDate
import java.util.Calendar

class CalendarAdapter(
    private val days: List<CalendarDay>,
    private val onDateClick: (LocalDate) -> Unit
) : RecyclerView.Adapter<CalendarAdapter.DayViewHolder>() {
    var date : LocalDate? = null
    inner class DayViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dayNumber = view.findViewById<TextView>(R.id.dayNumber)
        val dayName = view.findViewById<TextView>(R.id.dayName)
        val checkImg = view.findViewById<ImageView>(R.id.check)
        val fram = view.findViewById<FrameLayout>(R.id.fram)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_calendar, parent, false)
        return DayViewHolder(view)
    }

    override fun getItemCount(): Int = days.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val item = days[position]
        val context = holder.itemView.context
        val arabicDays = listOf(context.getString(R.string.sunday),
            context.getString(R.string.monday),
            context.getString(R.string.tuesday),
            context.getString(R.string.wednesday),
            context.getString(R.string.thursday), context.getString(R.string.friday),
            context.getString(
                R.string.saturday
            ))
        if (context is DetailsspeciallistActivity){
            if ((context as DetailsspeciallistActivity).detailstechnicalViewModel.calendertype.get() == 1){
                holder.dayNumber.text = item.date.dayOfMonth.toString()
            }else {
                val hijriCalendar = UmmalquraCalendar()
                hijriCalendar.time = item.date.toDate()
                val day = hijriCalendar.get(Calendar.DAY_OF_MONTH)
                holder.dayNumber.text = day.toString()
            }
        }
        holder.dayName.text = arabicDays[item.date.dayOfWeek % 7]
        try {
            if (item.date == date){
                holder.checkImg.visibility = View.VISIBLE
                holder.fram.setBackgroundResource(R.drawable.rec_strockblue2)
            }else {
                holder.checkImg.visibility = View.GONE
                holder.fram.setBackgroundResource(R.drawable.rec_strockblue1)
            }
        }catch (e: Exception){
            holder.checkImg.visibility = View.GONE
            holder.fram.setBackgroundResource(R.drawable.rec_strockblue1)
        }

        holder.itemView.setOnClickListener {
            date = item.date
            notifyDataSetChanged()
            onDateClick(item.date)
        }
    }
}
