package fudex.bonyad.Model

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
    val serviceId: Int? = null,
    val cityId: Int? = null
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

data class FilterorderModel(
    val status: Int? = null,
    val from: String? = null,
    val to: String? = null,

    )