package onnetysolutions.sadded.Model

import com.google.common.primitives.Booleans
import fudex.bonyad.Model.UserModel


/**
 * Created by hp on 6/3/2018.
 */

class FaqModel{
    val data: ArrayList<FaqdataModel>? = ArrayList()
}

class FaqdataModel{
    val id: Int? = null
    val question: String? = null
    val answer: String? = null
    var isshow: Boolean? = null
}

