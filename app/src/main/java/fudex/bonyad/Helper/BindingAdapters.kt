package fudex.bonyad.Helper

import android.app.Activity
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.net.Uri
import android.text.Html
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.databinding.BindingAdapter
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

import com.blongho.country_data.World
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import fudex.bonyad.Model.AddressesDatum
import fudex.bonyad.Model.Availability
import fudex.bonyad.Model.Certificate
import fudex.bonyad.Model.DistanceModel
import fudex.bonyad.Model.PlanData
import fudex.bonyad.Model.StatesDatum

import fudex.bonyad.R
import fudex.bonyad.ui.Adapter.technical.Certificatesadapter
import fudex.bonyad.ui.Adapter.technical.Imagessadapter
import fudex.bonyad.ui.Adapter.technical.Serviceadapter
import fudex.bonyad.ui.Adapter.technical.Serviceimageadapter
import fudex.bonyad.Model.RegisterimageModel
import fudex.bonyad.Model.Technician
import fudex.bonyad.ui.Adapter.technical.Avalibiltyadapter
import fudex.bonyad.ui.Adapter.technical.Daysadapter
import fudex.bonyad.ui.Adapter.technical.Planadapter
import fudex.bonyad.ui.Adapter.technical.Service1adapter
import fudex.bonyad.ui.Adapter.user.Addressesadapter
import fudex.bonyad.ui.Adapter.user.Technicafilterladapter
import fudex.bonyad.ui.Adapter.user.Technical1adapter
import fudex.bonyad.ui.Adapter.user.Technicaladapter


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
    @BindingAdapter("bind:imageUrl1")
    @JvmStatic
    fun loadImage1(imageView: ImageView, url: Uri) {
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
    @BindingAdapter("bind:certificate")
    @JvmStatic
    fun loadcertificat(imageView: ImageView, url: Certificate) {
        try {
//                GlobalScope.launch(Dispatchers.Main) {
            if (url.id != -1){
                if (url.id == 0){
                    Glide.with(imageView.context)
                        .load(url.uri) // Use the file path where the image is saved
                        .placeholder(R.mipmap.ic_launcher_round)
                        .into(imageView)
                }else {
                    Glide.with(imageView.context)
                        .load(url.url) // Use the file path where the image is saved
                        .placeholder(R.mipmap.ic_launcher_round)
                        .into(imageView)
                }
            }

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
    @BindingAdapter("bind:underline")
    @JvmStatic
    fun setunderline(textView: TextView, text: String) {
        textView.text = text
        textView.paintFlags = textView.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }
    @BindingAdapter("bind:images", "bind:fragment")
    @JvmStatic
    fun setimages(recyclerView: RecyclerView, movies: ArrayList<RegisterimageModel>, fragment: Activity) {
        val adapter = recyclerView.adapter
        if (adapter != null && adapter is Imagessadapter) {
            (adapter as Imagessadapter).setdata(movies, fragment)
        }else {
//            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
        }
    }
//
    @BindingAdapter("bind:certificates", "bind:fragment")
    @JvmStatic
    fun setcertificates(recyclerView: RecyclerView, movies: ArrayList<Certificate>, fragment: Activity) {
        val adapter = recyclerView.adapter
        if (adapter != null && adapter is Certificatesadapter) {
            (adapter as Certificatesadapter).setdata(movies, fragment)
        }else {
//            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
        }
    }
//
    @BindingAdapter("bind:services", "bind:fragment")
    @JvmStatic
    fun setservices(recyclerView: RecyclerView, movies: ArrayList<StatesDatum>, fragment: Fragment) {
        val adapter = recyclerView.adapter
        if (adapter != null && adapter is Serviceadapter) {
            (adapter as Serviceadapter).setdata(movies, fragment)
        }else {
//            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
        }
    }
//
    @BindingAdapter("bind:servicesimage", "bind:fragment")
    @JvmStatic
    fun setservicesimage(recyclerView: RecyclerView, movies: ArrayList<Certificate>, fragment: Activity) {
        val adapter = recyclerView.adapter
        if (adapter != null && adapter is Serviceimageadapter) {
            (adapter as Serviceimageadapter).setdata(movies, fragment)
        }else {
//            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
        }
    }
//
    @BindingAdapter("bind:days", "bind:fragment")
    @JvmStatic
    fun setdays(recyclerView: RecyclerView, movies: ArrayList<DistanceModel>, fragment: Activity) {
        val adapter = recyclerView.adapter
        if (adapter != null && adapter is Daysadapter) {
            (adapter as Daysadapter).setdata(movies, fragment)
        }else {
//            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
        }
    }
//
    @BindingAdapter("bind:availability", "bind:fragment")
    @JvmStatic
    fun setavailability(recyclerView: RecyclerView, movies: ArrayList<Availability>, fragment: Activity) {
        val adapter = recyclerView.adapter
        if (adapter != null && adapter is Avalibiltyadapter) {
            (adapter as Avalibiltyadapter).setdata(movies, fragment)
        }else {
//            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
        }
    }
//
    @BindingAdapter("bind:plan", "bind:fragment")
    @JvmStatic
    fun setplan(recyclerView: RecyclerView, movies: ArrayList<PlanData>, fragment: Activity) {
        val adapter = recyclerView.adapter
        if (adapter != null && adapter is Planadapter) {
            (adapter as Planadapter).setdata(movies, fragment)
        }else {
//            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
        }
    }

    @BindingAdapter("bind:services1", "bind:fragment")
    @JvmStatic
    fun setservices1(recyclerView: RecyclerView, movies: ArrayList<StatesDatum>, fragment: Activity) {
        val adapter = recyclerView.adapter
        if (adapter != null && adapter is Service1adapter) {
            (adapter as Service1adapter).setdata(movies, fragment)
        }else {
//            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
        }
    }
//
    @BindingAdapter("bind:technicalhome", "bind:fragment")
    @JvmStatic
    fun settechnicalhome(recyclerView: RecyclerView, movies: ArrayList<Technician>, fragment: Fragment) {
        val adapter = recyclerView.adapter
        if (adapter != null && adapter is Technicaladapter) {
            (adapter as Technicaladapter).setdata(movies, fragment)
        }else {
//            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
        }
    }

    @BindingAdapter("bind:technical", "bind:fragment")
    @JvmStatic
    fun settechnical(recyclerView: RecyclerView, movies: ArrayList<Technician>, fragment: Activity) {
        val adapter = recyclerView.adapter
        if (adapter != null && adapter is Technical1adapter) {
            (adapter as Technical1adapter).setdata(movies, fragment)
        }else {
//            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
        }
    }
//
    @BindingAdapter("bind:address", "bind:fragment")
    @JvmStatic
    fun setaddress(recyclerView: RecyclerView, movies: ArrayList<AddressesDatum>, fragment: Activity) {
        val adapter = recyclerView.adapter
        if (adapter != null && adapter is Addressesadapter) {
            (adapter as Addressesadapter).setdata(movies, fragment)
        }else {
//            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
        }
    }

    @BindingAdapter("bind:technicalfilter", "bind:fragment")
    @JvmStatic
    fun settechnicalfilter(recyclerView: RecyclerView, movies: ArrayList<StatesDatum>, fragment: Fragment) {
        val adapter = recyclerView.adapter
        if (adapter != null && adapter is Technicafilterladapter) {
            (adapter as Technicafilterladapter).setdata(movies, fragment)
        }else {
//            throw IllegalStateException("RecyclerView either has no adapter set or the " + "adapter isn't of type MovieAdapter")
        }
    }
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
