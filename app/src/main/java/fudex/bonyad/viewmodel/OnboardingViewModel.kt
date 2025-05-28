package fudex.superfan.viewmodel

import android.app.Activity
import android.content.Intent
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import fudex.bonyad.Model.OnbaordModel


/**
 * Created by BEST BUY on 5/8/2018.
 */

class OnboardingViewModel : BaseObservable() {
    var onservse = ObservableField<OnbaordModel>()
    var catModel : OnbaordModel? = null
    var context : Activity? = null
    fun setdata(catModel: OnbaordModel, context : Activity) {
        onservse.set(catModel)
        this.catModel = catModel
        this.context = context
    }
}
