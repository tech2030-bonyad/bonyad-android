package fudex.bonyad.ui.Adapter.merchant

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fudex.bonyad.R
import fudex.bonyad.databinding.MerchantHomeorderviewModelBinding
import fudex.bonyad.Model.RecentOrder
import fudex.bonyad.viewmodel.merchant.OrdersViewModel
import fudex.bonyad.viewmodel.technical.OrderViewModel
import java.util.*


/**
 * Created by سيد on 04/06/2017.
 */
class Ordersadapter : RecyclerView.Adapter<Ordersadapter.MyViewHolder>() {
    internal var context: Activity? = null
    private var horizontalList: List<RecentOrder> = ArrayList<RecentOrder>()
    internal lateinit var movieItem: MerchantHomeorderviewModelBinding

    fun setdata(movies: List<RecentOrder>, context: Activity) {
        this.horizontalList = movies
        this.context = context
        Log.e("date" , movies.size.toString())
        notifyDataSetChanged()
    }

    inner class MyViewHolder(designsBinding: MerchantHomeorderviewModelBinding) : RecyclerView.ViewHolder(designsBinding.getRoot()) {


        internal var designsBinding: MerchantHomeorderviewModelBinding
            internal set

        init {
            this.designsBinding = designsBinding
        }

        fun bind(design: OrdersViewModel) {
            designsBinding.model = design
            designsBinding.executePendingBindings()
        }

         fun getDesignsBinding(): MerchantHomeorderviewModelBinding {
            return designsBinding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        movieItem = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_merchantorders, parent, false)
        return MyViewHolder(movieItem)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val designs = horizontalList[position]
        val design = OrdersViewModel()
        design.setdata(designs , context!!)
        holder.bind(design)
    }

    override fun getItemCount(): Int {
        return horizontalList.size
    }


}
