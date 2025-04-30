package fudex.bonyad.Helper

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.Html
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.databinding.BindingAdapter
import androidx.drawerlayout.widget.DrawerLayout

import com.blongho.country_data.World
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

import fudex.bonyad.R



/**
 * Created by BEST BUY on 5/10/2018.
 */

object BindingAdapters {
    @BindingAdapter("bind:imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String) {
        Log.e("data",url)
            try {
//                GlobalScope.launch(Dispatchers.Main) {
                Glide.with(imageView.context)
                    .load(url) // Use the file path where the image is saved
                    .placeholder(R.mipmap.ic_launcher_round)
                    .into(imageView)

            } catch (e: Exception) {
                e.printStackTrace()
                imageView.setImageResource(R.mipmap.ic_launcher_round)
            }

    }
    @BindingAdapter("bind:sliderbUrl")
    @JvmStatic
    fun sliderbUrl(imageView: ImageView, url: String) {
        Log.e("data", url)
        try {
//                GlobalScope.launch(Dispatchers.Main) {
            Picasso.get()
                .load(url ?: "")
                .into(imageView)
//                }

        } catch (e: Exception) {
            e.printStackTrace()
           // imageView.setImageResource(R.drawable.defualtphoto)
        }
    }
    @BindingAdapter("bind:imageclubUrl")
    @JvmStatic
    fun imageclub(imageView: ImageView, url: String) {
        Log.e("data",url)
        try {
//                GlobalScope.launch(Dispatchers.Main) {
            Picasso.get()
                .load(url)
                .error(R.mipmap.ic_launcher_round)
                .into(imageView)
//                }

        } catch (e: Exception) {
            e.printStackTrace()
//            imageView.setImageResource(R.drawable.ic_all)
        }

    }
    @BindingAdapter("bind:imageclubUrl1")
    @JvmStatic
    fun imageclub1(imageView: ImageView, url: String) {
        Log.e("data",url)
        try {
//                GlobalScope.launch(Dispatchers.Main) {
            Picasso.get()
                .load(url)
                .error(R.mipmap.ic_launcher_round)
                .into(imageView)
//                }

        } catch (e: Exception) {
            e.printStackTrace()
//            imageView.setImageResource(R.drawable.pyramids)
        }

    }
    @BindingAdapter("bind:flag")
    @JvmStatic
    fun flag(imageView: ImageView, url: String) {
        try {
            var flag = World.getFlagOf(url)
            imageView.setImageResource(flag)

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
    @BindingAdapter("bind:drawable")
    @JvmStatic
    fun drawable(imageView: ImageView, url: Drawable) {
        try {
            imageView.setImageDrawable(url)

        } catch (e: Exception) {
            e.printStackTrace()
            imageView.setImageResource(R.mipmap.ic_launcher_round)
        }

    }
//
    @BindingAdapter("bind:draw")
    @JvmStatic
    fun opendrawable(drawerLayout: DrawerLayout, url: String) {
        Log.e("data", url)
        if (url.equals("0")) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else if (url.equals("1")) {
            drawerLayout.openDrawer(GravityCompat.START)
        }

    }
    @BindingAdapter("bind:rate")
    @JvmStatic
    fun rate(ratebar: RatingBar, rate: String) {
        if (rate != "") {
            ratebar.rating = rate.toFloat()
        } else {
            ratebar.rating = "0".toFloat()
        }
    }
    @BindingAdapter("bind:cardcolor")
    @JvmStatic
    fun setcardcolor(cardView: CardView, color: String) {
       try {
           if (color.length > 0){
               cardView.setCardBackgroundColor(Color.parseColor(color))
           }
       }catch (e :Exception){

       }
    }
    @BindingAdapter("bind:colorText")
    @JvmStatic
    fun setFontColor(textView: TextView, color: String) {
        textView.setTextColor(Color.parseColor(color))

    }
    @BindingAdapter("bind:rotate")
    @JvmStatic
    fun setrotate(textView: TextView, color: String) {
       if (textView.context.getString(R.string.lang) == "ar"){
           textView.rotation = -47.05f
       }
    }
    @BindingAdapter("bind:html")
    @JvmStatic
    fun sethtml(textView: TextView, txt: String) {
        textView.setText(Html.fromHtml(txt))

    }
//    @BindingAdapter("bind:faqs", "bind:fragment")
//    @JvmStatic
//    fun setfaqs(recyclerView: RecyclerView, movies: ArrayList<FaqdataModel>, fragment: Activity) {
//        val adapter = recyclerView.adapter
//        if (adapter != null && adapter is Faqadapter) {
//            (adapter as Faqadapter).setdata(movies, fragment)
//        }else {
////            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
//        }
//    }
//
//    @BindingAdapter("bind:addresses", "bind:fragment")
//    @JvmStatic
//    fun setaddresses(recyclerView: RecyclerView, movies: ArrayList<AddressesDatum>, fragment: Activity) {
//        val adapter = recyclerView.adapter
//        if (adapter != null && adapter is Addressesadapter) {
//            (adapter as Addressesadapter).setdata(movies, fragment)
//        }else {
////            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
//        }
//    }
//
//    @BindingAdapter("bind:changeaddresses", "bind:fragment")
//    @JvmStatic
//    fun setchangeaddresses(recyclerView: RecyclerView, movies: ArrayList<AddressesDatum>, fragment: Fragment) {
//        val adapter = recyclerView.adapter
//        if (adapter != null && adapter is Changeaddressesadapter) {
//            (adapter as Changeaddressesadapter).setdata(movies, fragment)
//        }else {
////            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
//        }
//    }
//
//    @BindingAdapter("bind:branches", "bind:fragment")
//    @JvmStatic
//    fun setbranches(recyclerView: RecyclerView, movies: ArrayList<BranchDatum>, fragment: Fragment) {
//        val adapter = recyclerView.adapter
//        if (adapter != null && adapter is Branchesadapter) {
//            (adapter as Branchesadapter).setdata(movies, fragment)
//        }else {
////            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
//        }
//    }
//
//    @BindingAdapter("bind:orders", "bind:fragment")
//    @JvmStatic
//    fun setorders(recyclerView: RecyclerView, movies: ArrayList<OrderDatum>, fragment: Fragment) {
//        val adapter = recyclerView.adapter
//        if (adapter != null && adapter is Ordersadapter) {
//            (adapter as Ordersadapter).setdata(movies, fragment)
//        }else {
////            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
//        }
//    }
//
//    @BindingAdapter("bind:servicestype", "bind:fragment")
//    @JvmStatic
//    fun setservicestype(recyclerView: RecyclerView, movies: ArrayList<String>, fragment: Fragment) {
//        val adapter = recyclerView.adapter
//        if (adapter != null && adapter is Servicestypeadapter) {
//            (adapter as Servicestypeadapter).setdata(movies, fragment)
//        }else {
////            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
//        }
//    }
//
//    @BindingAdapter("bind:servicestypeselect", "bind:fragment")
//    @JvmStatic
//    fun setservicestypeselect(recyclerView: RecyclerView, movies: ArrayList<ServicetypeDatum>, fragment: Fragment) {
//        val adapter = recyclerView.adapter
//        if (adapter != null && adapter is Servicestypeselectadapter) {
//            (adapter as Servicestypeselectadapter).setdata(movies, fragment)
//        }else {
////            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
//        }
//    }
//
//    @BindingAdapter("bind:servicestypeselect1", "bind:fragment")
//    @JvmStatic
//    fun setservicestypeselec1t(recyclerView: RecyclerView, movies: ArrayList<ServicetypeDatum>, fragment: Activity) {
//        val adapter = recyclerView.adapter
//        if (adapter != null && adapter is Servicestypeselect1adapter) {
//            (adapter as Servicestypeselect1adapter).setdata(movies, fragment)
//        }else {
////            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
//        }
//    }
//
//    @BindingAdapter("bind:services", "bind:fragment")
//    @JvmStatic
//    fun setservices(recyclerView: RecyclerView, movies: ArrayList<Service>, fragment: Activity) {
//        val adapter = recyclerView.adapter
//        if (adapter != null && adapter is Servicesadapter) {
//            (adapter as Servicesadapter).setdata(movies, fragment)
//        }else {
////            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
//        }
//    }
//
//    @BindingAdapter("bind:servicesorder", "bind:fragment")
//    @JvmStatic
//    fun setservicesorder(recyclerView: RecyclerView, movies: ArrayList<Service>, fragment: Activity) {
//        val adapter = recyclerView.adapter
//        if (adapter != null && adapter is Servicesdetailsadapter) {
//            (adapter as Servicesdetailsadapter).setdata(movies, fragment)
//        }else {
////            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
//        }
//    }
//    @BindingAdapter("bind:servicesbook", "bind:fragment")
//    @JvmStatic
//    fun setservicesbook(recyclerView: RecyclerView, movies: ArrayList<Service>, fragment: Activity) {
//        val adapter = recyclerView.adapter
//        if (adapter != null && adapter is Servicesbookingadapter) {
//            (adapter as Servicesbookingadapter).setdata(movies, fragment)
//        }else {
////            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
//        }
//    }
//    @BindingAdapter("bind:servicesslots", "bind:fragment")
//    @JvmStatic
//    fun setservicesslots(recyclerView: RecyclerView, movies: ArrayList<SlotsDatum>, fragment: Activity) {
//        val adapter = recyclerView.adapter
//        if (adapter != null && adapter is Servicesslotsadapter) {
//            (adapter as Servicesslotsadapter).setdata(movies, fragment)
//        }else {
////            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
//        }
//    }
//    @BindingAdapter("bind:slots", "bind:fragment" , "bind:bind")
//    @JvmStatic
//    fun setlots(recyclerView: RecyclerView, movies: ArrayList<Slot>, fragment: Activity , bind : ServicesslotsViewModelBinding) {
//        val adapter = recyclerView.adapter
//        if (adapter != null && adapter is Slotsadapter) {
//            (adapter as Slotsadapter).setdata(movies, fragment,bind)
//        }else {
////            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
//        }
//    }
//
//    @BindingAdapter("bind:rating", "bind:fragment")
//    @JvmStatic
//    fun setrating(recyclerView: RecyclerView, movies: ArrayList<Rating>, fragment: Activity) {
//        val adapter = recyclerView.adapter
//        if (adapter != null && adapter is Rateingadapter) {
//            (adapter as Rateingadapter).setdata(movies, fragment)
//        }else {
////            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
//        }
//    }
//
//    @BindingAdapter("bind:rating1", "bind:fragment")
//    @JvmStatic
//    fun setrating1(recyclerView: RecyclerView, movies: ArrayList<Rating>, fragment: Activity) {
//        val adapter = recyclerView.adapter
//        if (adapter != null && adapter is Rateing1adapter) {
//            (adapter as Rateing1adapter).setdata(movies, fragment)
//        }else {
////            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
//        }
//    }
//    @BindingAdapter("bind:complain", "bind:fragment")
//    @JvmStatic
//    fun setcomplain(recyclerView: RecyclerView, movies: ArrayList<ComplainDatum>, fragment: Activity) {
//        val adapter = recyclerView.adapter
//        if (adapter != null && adapter is Complainadapter) {
//            (adapter as Complainadapter).setdata(movies, fragment)
//        }else {
////            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
//        }
//    }
//
//    @BindingAdapter("bind:nots", "bind:fragment")
//    @JvmStatic
//    fun setnots(recyclerView: RecyclerView, movies: ArrayList<NotsDatum>, fragment: Activity) {
//        val adapter = recyclerView.adapter
//        if (adapter != null && adapter is Notsadapter) {
//            (adapter as Notsadapter).setdata(movies, fragment)
//        }else {
////            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
//        }
//    }
//    @BindingAdapter("bind:notsdata", "bind:fragment")
//    @JvmStatic
//    fun setnotsdata(recyclerView: RecyclerView, movies: ArrayList<Notification>, fragment: Activity) {
//        val adapter = recyclerView.adapter
//        if (adapter != null && adapter is Notsdataadapter) {
//            (adapter as Notsdataadapter).setdata(movies, fragment)
//        }else {
////            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
//        }
//    }
}
