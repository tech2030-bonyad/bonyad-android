package fudex.bonyad.viewmodel

import android.content.Context
import android.content.Intent
import androidx.databinding.BaseObservable
import dev.b3nedikt.app_locale.AppLocale
import fudex.bonyad.Model.OnbaordModel
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Activity.LoginActivity
import fudex.bonyad.ui.Activity.OnboardingActivity
import fudex.bonyad.ui.Activity.SplashActivity
import fudex.bonyad.ui.Activity.technical.TechnicalHomeActivity
import fudex.bonyad.ui.Activity.user.UserhomeActivity
import fudex.bonyad.ui.Adapter.OnboardPagerAdapter


import java.util.Locale
import kotlin.collections.ArrayList

class OnboardingListViewModel(var catogaryFragment: OnboardingActivity) : BaseObservable() {
    var activity: OnboardingActivity = OnboardingActivity()
    var items : ArrayList<OnbaordModel> = ArrayList()
    lateinit var pagerAdapter: OnboardPagerAdapter

    init {
        this.activity = catogaryFragment
        items.clear()
        if (LoginSession.gettype(activity) == 1) {
            items.add(OnbaordModel(
                R.drawable.useronboard1 ,
                activity.getString(R.string.all_construction_needs_are_in_your_hands),
                activity.getString(R.string.browse_thousands_of_products_from_cement_and_tools_to_paints_and_decor_and_choose_what_suits_your_project_with_the_click_of_a_button)))
            items.add(OnbaordModel(
                R.drawable.useronboard2 ,
                activity.getString(R.string.all_construction_needs_are_in_your_hands) ,
                activity.getString(R.string.browse_thousands_of_products_from_cement_and_tools_to_paints_and_decor_and_choose_what_suits_your_project_with_the_click_of_a_button)))
            items.add(OnbaordModel(
                R.drawable.useronboard3 ,
                activity.getString(R.string.ready_to_adopt_we_will_provide_you_with_everything_you_need) ,
                activity.getString(R.string.browse_thousands_of_products_from_cement_and_tools_to_paints_and_decor_and_choose_what_suits_your_project_with_the_click_of_a_button)))
        }else if (LoginSession.gettype(activity) == 2) {
            items.add(OnbaordModel(
                R.drawable.useronboard1 ,
                activity.getString(R.string.all_construction_needs_are_in_your_hands),
                activity.getString(R.string.browse_thousands_of_products_from_cement_and_tools_to_paints_and_decor_and_choose_what_suits_your_project_with_the_click_of_a_button)))
            items.add(OnbaordModel(
                R.drawable.useronboard2 ,
                activity.getString(R.string.all_construction_needs_are_in_your_hands) ,
                activity.getString(R.string.browse_thousands_of_products_from_cement_and_tools_to_paints_and_decor_and_choose_what_suits_your_project_with_the_click_of_a_button)))
            items.add(OnbaordModel(
                R.drawable.useronboard3 ,
                activity.getString(R.string.ready_to_adopt_we_will_provide_you_with_everything_you_need) ,
                activity.getString(R.string.browse_thousands_of_products_from_cement_and_tools_to_paints_and_decor_and_choose_what_suits_your_project_with_the_click_of_a_button)))
        }else if (LoginSession.gettype(activity) == 3) {
            items.add(OnbaordModel(
                R.drawable.technicalonboard1 ,
                activity.getString(R.string.reliable_labor),
                activity.getString(R.string.check_the_reviews_and_choose_the_worker_that_suits_you)))
            items.add(OnbaordModel(
                R.drawable.technicalonboard2 ,
                activity.getString(R.string.your_work_is_ready_and_waiting_for_you) ,
                activity.getString(R.string.nearby_requests_direct_money_and_you_control_your_time)))
            items.add(OnbaordModel(
                R.drawable.technocalonboard3 ,
                activity.getString(R.string.today_s_task_from_the_application) ,
                activity.getString(R.string.with_you_every_step_of_the_way_in_your_career_journey)))
        }
        pagerAdapter = OnboardPagerAdapter(activity)
        (activity as OnboardingActivity).binding.pager.setAdapter(pagerAdapter)
        activity.binding.indicator.setViewPager(activity.binding.pager)
        pagerAdapter.registerDataSetObserver(activity.binding.indicator.getDataSetObserver())
        if (activity.getString(R.string.lang) == "ar") {
            activity.binding.pager.rotationY = 180f
        }

        notifyChange()
    }

    fun next(){
        if ((activity as OnboardingActivity).binding.pager.currentItem == 2){
            LoginSession.Addwelcome(activity,true)
            if (LoginSession.isLogin){
                if (LoginSession.gettype(activity) == 1){
                    var intent: Intent = Intent(activity, UserhomeActivity::class.java)
                    activity.startActivity(intent)
                    activity.finish()
                }else if (LoginSession.gettype(activity) == 3){
                    var intent: Intent = Intent(activity, TechnicalHomeActivity::class.java)
                    activity.startActivity(intent)
                    activity.finish()
                }
            }else {
                var intent: Intent = Intent(activity, LoginActivity::class.java)
                activity.startActivity(intent)
                activity.finish()
            }
//
        }else {
            (activity as OnboardingActivity).binding.pager.setCurrentItem((activity as OnboardingActivity).binding.pager.currentItem + 1)
        }
    }
    fun skip(){
        LoginSession.Addwelcome(activity,true)
        if (LoginSession.isLogin){
            if (LoginSession.gettype(activity) == 1){
                var intent: Intent = Intent(activity, UserhomeActivity::class.java)
                activity.startActivity(intent)
                activity.finish()
            }else if (LoginSession.gettype(activity) == 3){
                var intent: Intent = Intent(activity, TechnicalHomeActivity::class.java)
                activity.startActivity(intent)
                activity.finish()
            }
        }else {
            var intent: Intent = Intent(activity, LoginActivity::class.java)
            activity.startActivity(intent)
            activity.finish()
        }
    }
    fun lang(){
        if (activity.getString(R.string.lang) == "ar"){
            var loginFile = activity.getSharedPreferences("lang", Context.MODE_PRIVATE)
            val editor = loginFile!!.edit()
            editor.putString("lang", "en")
            editor.apply()
            AppLocale.desiredLocale = Locale.ENGLISH
            val intent = Intent(activity, SplashActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            activity.finish()
            activity.startActivity(intent)
        }else{
            var loginFile = activity.getSharedPreferences("lang", Context.MODE_PRIVATE)
            val editor = loginFile!!.edit()
            editor.putString("lang", "ar")
            editor.apply()
            AppLocale.desiredLocale = Locale("ar")
            val intent = Intent(activity, SplashActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            activity.finish()
            activity.startActivity(intent)
        }
    }
}