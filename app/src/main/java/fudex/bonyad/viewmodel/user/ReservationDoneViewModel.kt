package fudex.bonyad.viewmodel.user

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import androidx.databinding.BaseObservable
import fudex.bonyad.ui.Activity.user.UserhomeActivity
import fudex.bonyad.ui.Fragment.user.SpeciallistreservedoneFragment


@SuppressLint("UseRequireInsteadOfGet")
class ReservationDoneViewModel(var catogaryFragment: SpeciallistreservedoneFragment) : BaseObservable() {
    var context: SpeciallistreservedoneFragment = SpeciallistreservedoneFragment()
    init {
        this.context = catogaryFragment
        Handler().postDelayed(Runnable {
            var intent: Intent = Intent(context.requireActivity(), UserhomeActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(intent)
            context.requireActivity().finish()
            context.dismiss()
        },4000)
    }


}