package onnetysolutions.sadded.Model

import com.google.common.primitives.Booleans
import fudex.bonyad.Model.UserModel
import org.joda.time.LocalDate


/**
 * Created by hp on 6/3/2018.
 */

data class DistanceModel(
    val id: Int? = null,
    val title: String? = null
)


data class FilterModel(
    val service_type: String? = null,
    val max_distance: String? = null,
    val location_type: String? = null,
    val employee_gender: String? = null,
    val rate: String? = null,
)

data class CalendarDay(
    val date: LocalDate,
    val isToday: Boolean,
    val isSelected: Boolean
)
data class GiftModel(
    val name: String? = null,
    val phone: String? = null,
    val loc: String? = null,
    val gender: String? = null,
)