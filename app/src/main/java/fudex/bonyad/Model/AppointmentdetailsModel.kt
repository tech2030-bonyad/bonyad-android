package fudex.bonyad.Model

data class AppointmentdetailsModel (
    val data: AppointmentdetailsData? = null
)

data class AppointmentdetailsData (
    val id: Int? = null,
    val user: User? = null,
    val technician: Technician? = null,
    val date_of_reservation: String? = null,
    val day: Day? = null,
    val start_time: String? = null,
    val end_time: String? = null,
    val lat: String? = null,
    val long: String? = null,
    val status: Day? = null,
    val notes: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null
)


