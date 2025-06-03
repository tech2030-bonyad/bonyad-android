package fudex.bonyad.Helper;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.databinding.BindingAdapter;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.blongho.country_data.World;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import fudex.bonyad.Model.AddressesDatum;
import fudex.bonyad.Model.Availability;
import fudex.bonyad.Model.Certificate;
import fudex.bonyad.Model.DistanceModel;
import fudex.bonyad.Model.OnbaordModel;
import fudex.bonyad.Model.OrdersDatum;
import fudex.bonyad.Model.PlanData;
import fudex.bonyad.Model.StatesDatum;
import fudex.bonyad.R;
import fudex.bonyad.ui.Adapter.technical.Certificatesadapter;
import fudex.bonyad.ui.Adapter.technical.Imagessadapter;
import fudex.bonyad.ui.Adapter.technical.Serviceadapter;
import fudex.bonyad.ui.Adapter.technical.Serviceimageadapter;
import fudex.bonyad.Model.RegisterimageModel;
import fudex.bonyad.Model.Technician;
import fudex.bonyad.ui.Adapter.OnboardPagerAdapter;
import fudex.bonyad.ui.Adapter.technical.Avalibiltyadapter;
import fudex.bonyad.ui.Adapter.technical.Daysadapter;
import fudex.bonyad.ui.Adapter.technical.Planadapter;
import fudex.bonyad.ui.Adapter.technical.Service1adapter;
import fudex.bonyad.ui.Adapter.user.Addressesadapter;
import fudex.bonyad.ui.Adapter.user.Ordersadapter;
import fudex.bonyad.ui.Adapter.user.Servicesdetailsadapter;
import fudex.bonyad.ui.Adapter.user.Technicafilterladapter;
import fudex.bonyad.ui.Adapter.user.Technical1adapter;
import fudex.bonyad.ui.Adapter.user.Technicaladapter;
import fudex.bonyad.ui.Adapter.user.Timesadapter;

/**
 * Created by BEST BUY on 5/10/2018.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00b0\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\nH\u0007J\u0018\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\nH\u0007J\u0018\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\nH\u0007J\u0018\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\nH\u0007J\u0018\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u000fH\u0007J\u0018\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0011H\u0007J\u0018\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\nH\u0007J\u0018\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00020\nH\u0007J\u0018\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\nH\u0007J0\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001e2\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020!0 j\b\u0012\u0004\u0012\u00020!`\"2\u0006\u0010#\u001a\u00020$H\u0007J0\u0010%\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001e2\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020&0 j\b\u0012\u0004\u0012\u00020&`\"2\u0006\u0010#\u001a\u00020$H\u0007J\u0018\u0010\'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020)2\u0006\u0010\u001b\u001a\u00020\nH\u0007J0\u0010*\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001e2\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020\u00110 j\b\u0012\u0004\u0012\u00020\u0011`\"2\u0006\u0010#\u001a\u00020$H\u0007J0\u0010+\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001e2\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020,0 j\b\u0012\u0004\u0012\u00020,`\"2\u0006\u0010#\u001a\u00020$H\u0007J\u0018\u0010-\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020\nH\u0007J0\u0010/\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001e2\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u0002000 j\b\u0012\u0004\u0012\u000200`\"2\u0006\u0010#\u001a\u00020$H\u0007J(\u00101\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u0002022\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u0002030 j\b\u0012\u0004\u0012\u000203`\"H\u0007J0\u00104\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001e2\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u0002050 j\b\u0012\u0004\u0012\u000205`\"2\u0006\u0010#\u001a\u000206H\u0007J0\u00107\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001e2\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u0002080 j\b\u0012\u0004\u0012\u000208`\"2\u0006\u0010#\u001a\u00020$H\u0007J\u0018\u00109\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\nH\u0007J0\u0010:\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001e2\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020;0 j\b\u0012\u0004\u0012\u00020;`\"2\u0006\u0010#\u001a\u00020$H\u0007J0\u0010<\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001e2\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020;0 j\b\u0012\u0004\u0012\u00020;`\"2\u0006\u0010#\u001a\u000206H\u0007J0\u0010=\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001e2\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020;0 j\b\u0012\u0004\u0012\u00020;`\"2\u0006\u0010#\u001a\u00020$H\u0007J0\u0010>\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001e2\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020\u00110 j\b\u0012\u0004\u0012\u00020\u0011`\"2\u0006\u0010#\u001a\u00020$H\u0007J0\u0010?\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001e2\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020@0 j\b\u0012\u0004\u0012\u00020@`\"2\u0006\u0010#\u001a\u00020$H\u0007J0\u0010A\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001e2\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020;0 j\b\u0012\u0004\u0012\u00020;`\"2\u0006\u0010#\u001a\u000206H\u0007J0\u0010B\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001e2\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020@0 j\b\u0012\u0004\u0012\u00020@`\"2\u0006\u0010#\u001a\u000206H\u0007J0\u0010C\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001e2\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020&0 j\b\u0012\u0004\u0012\u00020&`\"2\u0006\u0010#\u001a\u00020$H\u0007J\u0018\u0010D\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010E\u001a\u00020\nH\u0007J\u0018\u0010F\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\nH\u0007\u00a8\u0006G"}, d2 = {"Lfudex/bonyad/Helper/BindingAdapters;", "", "()V", "drawable", "", "imageView", "Landroid/widget/ImageView;", "url", "Landroid/graphics/drawable/Drawable;", "flag", "", "imageclub", "imageclub1", "loadImage", "loadImage1", "Landroid/net/Uri;", "loadcertificat", "Lfudex/bonyad/Model/Certificate;", "opendrawable", "drawerLayout", "Landroidx/drawerlayout/widget/DrawerLayout;", "rate", "ratebar", "Landroid/widget/RatingBar;", "setFontColor", "textView", "Landroid/widget/TextView;", "color", "setaddress", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "movies", "Ljava/util/ArrayList;", "Lfudex/bonyad/Model/AddressesDatum;", "Lkotlin/collections/ArrayList;", "fragment", "Landroid/app/Activity;", "setavailability", "Lfudex/bonyad/Model/Availability;", "setcardcolor", "cardView", "Landroidx/cardview/widget/CardView;", "setcertificates", "setdays", "Lfudex/bonyad/Model/DistanceModel;", "sethtml", "txt", "setimages", "Lfudex/bonyad/Model/RegisterimageModel;", "setonboarding", "Landroidx/viewpager/widget/ViewPager;", "Lfudex/bonyad/Model/OnbaordModel;", "setorders", "Lfudex/bonyad/Model/OrdersDatum;", "Landroidx/fragment/app/Fragment;", "setplan", "Lfudex/bonyad/Model/PlanData;", "setrotate", "setservicedetails", "Lfudex/bonyad/Model/StatesDatum;", "setservices", "setservices1", "setservicesimage", "settechnical", "Lfudex/bonyad/Model/Technician;", "settechnicalfilter", "settechnicalhome", "settimes", "setunderline", "text", "sliderbUrl", "app_debug"})
public final class BindingAdapters {
    @org.jetbrains.annotations.NotNull()
    public static final fudex.bonyad.Helper.BindingAdapters INSTANCE = null;
    
    private BindingAdapters() {
        super();
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:imageUrl"})
    @kotlin.jvm.JvmStatic()
    public static final void loadImage(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    java.lang.String url) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:imageUrl1"})
    @kotlin.jvm.JvmStatic()
    public static final void loadImage1(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    android.net.Uri url) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:certificate"})
    @kotlin.jvm.JvmStatic()
    public static final void loadcertificat(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Model.Certificate url) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:sliderbUrl"})
    @kotlin.jvm.JvmStatic()
    public static final void sliderbUrl(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    java.lang.String url) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:imageclubUrl"})
    @kotlin.jvm.JvmStatic()
    public static final void imageclub(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    java.lang.String url) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:imageclubUrl1"})
    @kotlin.jvm.JvmStatic()
    public static final void imageclub1(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    java.lang.String url) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:flag"})
    @kotlin.jvm.JvmStatic()
    public static final void flag(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    java.lang.String url) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:drawable"})
    @kotlin.jvm.JvmStatic()
    public static final void drawable(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    android.graphics.drawable.Drawable url) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:draw"})
    @kotlin.jvm.JvmStatic()
    public static final void opendrawable(@org.jetbrains.annotations.NotNull()
    androidx.drawerlayout.widget.DrawerLayout drawerLayout, @org.jetbrains.annotations.NotNull()
    java.lang.String url) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:rate"})
    @kotlin.jvm.JvmStatic()
    public static final void rate(@org.jetbrains.annotations.NotNull()
    android.widget.RatingBar ratebar, @org.jetbrains.annotations.NotNull()
    java.lang.String rate) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:cardcolor"})
    @kotlin.jvm.JvmStatic()
    public static final void setcardcolor(@org.jetbrains.annotations.NotNull()
    androidx.cardview.widget.CardView cardView, @org.jetbrains.annotations.NotNull()
    java.lang.String color) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:colorText"})
    @kotlin.jvm.JvmStatic()
    public static final void setFontColor(@org.jetbrains.annotations.NotNull()
    android.widget.TextView textView, @org.jetbrains.annotations.NotNull()
    java.lang.String color) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:rotate"})
    @kotlin.jvm.JvmStatic()
    public static final void setrotate(@org.jetbrains.annotations.NotNull()
    android.widget.TextView textView, @org.jetbrains.annotations.NotNull()
    java.lang.String color) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:html"})
    @kotlin.jvm.JvmStatic()
    public static final void sethtml(@org.jetbrains.annotations.NotNull()
    android.widget.TextView textView, @org.jetbrains.annotations.NotNull()
    java.lang.String txt) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:underline"})
    @kotlin.jvm.JvmStatic()
    public static final void setunderline(@org.jetbrains.annotations.NotNull()
    android.widget.TextView textView, @org.jetbrains.annotations.NotNull()
    java.lang.String text) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:images", "bind:fragment"})
    @kotlin.jvm.JvmStatic()
    public static final void setimages(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView recyclerView, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<fudex.bonyad.Model.RegisterimageModel> movies, @org.jetbrains.annotations.NotNull()
    android.app.Activity fragment) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:certificates", "bind:fragment"})
    @kotlin.jvm.JvmStatic()
    public static final void setcertificates(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView recyclerView, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<fudex.bonyad.Model.Certificate> movies, @org.jetbrains.annotations.NotNull()
    android.app.Activity fragment) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:services", "bind:fragment"})
    @kotlin.jvm.JvmStatic()
    public static final void setservices(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView recyclerView, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<fudex.bonyad.Model.StatesDatum> movies, @org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment fragment) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:servicesimage", "bind:fragment"})
    @kotlin.jvm.JvmStatic()
    public static final void setservicesimage(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView recyclerView, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<fudex.bonyad.Model.Certificate> movies, @org.jetbrains.annotations.NotNull()
    android.app.Activity fragment) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:days", "bind:fragment"})
    @kotlin.jvm.JvmStatic()
    public static final void setdays(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView recyclerView, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<fudex.bonyad.Model.DistanceModel> movies, @org.jetbrains.annotations.NotNull()
    android.app.Activity fragment) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:availability", "bind:fragment"})
    @kotlin.jvm.JvmStatic()
    public static final void setavailability(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView recyclerView, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<fudex.bonyad.Model.Availability> movies, @org.jetbrains.annotations.NotNull()
    android.app.Activity fragment) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:plan", "bind:fragment"})
    @kotlin.jvm.JvmStatic()
    public static final void setplan(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView recyclerView, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<fudex.bonyad.Model.PlanData> movies, @org.jetbrains.annotations.NotNull()
    android.app.Activity fragment) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:services1", "bind:fragment"})
    @kotlin.jvm.JvmStatic()
    public static final void setservices1(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView recyclerView, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<fudex.bonyad.Model.StatesDatum> movies, @org.jetbrains.annotations.NotNull()
    android.app.Activity fragment) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:technicalhome", "bind:fragment"})
    @kotlin.jvm.JvmStatic()
    public static final void settechnicalhome(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView recyclerView, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<fudex.bonyad.Model.Technician> movies, @org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment fragment) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:technical", "bind:fragment"})
    @kotlin.jvm.JvmStatic()
    public static final void settechnical(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView recyclerView, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<fudex.bonyad.Model.Technician> movies, @org.jetbrains.annotations.NotNull()
    android.app.Activity fragment) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:address", "bind:fragment"})
    @kotlin.jvm.JvmStatic()
    public static final void setaddress(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView recyclerView, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<fudex.bonyad.Model.AddressesDatum> movies, @org.jetbrains.annotations.NotNull()
    android.app.Activity fragment) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:technicalfilter", "bind:fragment"})
    @kotlin.jvm.JvmStatic()
    public static final void settechnicalfilter(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView recyclerView, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<fudex.bonyad.Model.StatesDatum> movies, @org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment fragment) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:times", "bind:fragment"})
    @kotlin.jvm.JvmStatic()
    public static final void settimes(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView recyclerView, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<fudex.bonyad.Model.Availability> movies, @org.jetbrains.annotations.NotNull()
    android.app.Activity fragment) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:servicedetails", "bind:fragment"})
    @kotlin.jvm.JvmStatic()
    public static final void setservicedetails(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView recyclerView, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<fudex.bonyad.Model.StatesDatum> movies, @org.jetbrains.annotations.NotNull()
    android.app.Activity fragment) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:onboarding"})
    @kotlin.jvm.JvmStatic()
    public static final void setonboarding(@org.jetbrains.annotations.NotNull()
    androidx.viewpager.widget.ViewPager recyclerView, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<fudex.bonyad.Model.OnbaordModel> movies) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"bind:orders", "bind:fragment"})
    @kotlin.jvm.JvmStatic()
    public static final void setorders(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView recyclerView, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<fudex.bonyad.Model.OrdersDatum> movies, @org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment fragment) {
    }
}