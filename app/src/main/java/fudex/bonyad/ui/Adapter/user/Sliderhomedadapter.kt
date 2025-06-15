package fudex.bonyad.ui.Adapter.user

import android.app.Activity
import android.media.Image

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.PagerAdapter

import com.squareup.picasso.Picasso
import fudex.bonyad.Helper.IntentClass
import fudex.bonyad.Model.Slider
import fudex.bonyad.R


import java.util.ArrayList
import kotlin.Exception

/**
 * Created by سيد on 04/06/2017.
 */
class Sliderhomedadapter(
    context: Activity,
    slidesDataBeans: ArrayList<Slider>
) : PagerAdapter() {
    var mContext: Activity
    var mLayoutInflater: LayoutInflater
    var slidesDataBeans: ArrayList<Slider> =
        ArrayList<Slider>()
    private var sliderImg: ImageView? = null
    private var img: ImageView? = null
    private var des: TextView? = null
    private var title: TextView? = null

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return slidesDataBeans.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView: View = mLayoutInflater.inflate(R.layout.item_slider, container, false)
        initView(itemView)
        try {
            Picasso.get().load(slidesDataBeans[position].image).error(R.drawable.bonyad_logo_08_1).into(img)
        } catch (e: Exception) {
        }
        title?.text = slidesDataBeans[position].name ?: ""
        des?.text = slidesDataBeans[position].description ?: ""
        if (mContext.getString(R.string.lang) == "ar") {
            itemView.rotationY = 180f
        }
        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as FrameLayout)
    }

    private fun initView(view: View) {
        img = view.findViewById(R.id.img)
        title = view.findViewById(R.id.title)
        des = view.findViewById(R.id.des)
    }

    init {
        this.slidesDataBeans = slidesDataBeans
        mContext = context
        mLayoutInflater =
            mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }
}