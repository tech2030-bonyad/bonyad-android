package fudex.bonyad.ui.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import java.util.ArrayList
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import fudex.bonyad.Model.OnbaordModel
import fudex.bonyad.R


/**
 * Created by ATIAF on 3/5/2018.
 */

class OnboardPagerAdapter(internal var activity: Activity) : PagerAdapter() {

    var images: ArrayList<OnbaordModel> = ArrayList<OnbaordModel>()
    override fun getCount(): Int {
        return images.size
    }

    fun setdata(images: ArrayList<OnbaordModel>) {
        this.images = images
        notifyDataSetChanged()
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    @SuppressLint("MissingInflatedId")
    override fun instantiateItem(container: ViewGroup, position: Int): View {
        var layoutInflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.item_onboarding, null)
        val imageView = view.findViewById(R.id.img) as ImageView
        val title = view.findViewById(R.id.title) as TextView
        val des = view.findViewById(R.id.des) as TextView
        title.setText(images.get(position).title)
        des.setText(images.get(position).des)
        imageView.setImageResource(images.get(position).img)
        if (activity.getString(R.string.lang) == "ar") {
            view.rotationY = 180f
        }
        val vp = container as ViewPager
        vp.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, i: Int, obj: Any) {
        container.removeView(obj as View)
    }
}
