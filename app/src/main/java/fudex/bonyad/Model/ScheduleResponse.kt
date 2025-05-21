package fudex.bonyad.Model

import kotlin.collections.ArrayList

data class ScheduleResponse(
    val data: ArrayList<DayAvailability>? = ArrayList()
)

data class DayAvailability(
    val day_of_week: Int?,
    val day_name: String?,
    val availabilities: ArrayList<Availability>? = ArrayList()
)

data class Availability(
    val id: Int?,
    val day_of_week: Int?,
    val day_name: String?,
    var start_time: String?,
    var end_time: String?
)
data class Dateadd(
    val dates: ArrayList<Availabilityadd>? = ArrayList()
)
data class Availabilityadd(
    val day_of_week: Int?,
    var start_time: String?,
    var end_time: String?
)