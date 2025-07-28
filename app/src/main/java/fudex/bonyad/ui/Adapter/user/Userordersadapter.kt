package fudex.bonyad.ui.Adapter.user

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fudex.bonyad.R
import fudex.bonyad.databinding.UserorderitemviewModelBinding
import fudex.bonyad.Model.RecentOrder
import fudex.bonyad.viewmodel.technical.OrderViewModel
import fudex.bonyad.viewmodel.user.UserordersViewModel
import java.util.*


/**
 * Created by سيد on 04/06/2017.
 */
class Userordersadapter : RecyclerView.Adapter<Userordersadapter.MyViewHolder>() {
    internal var context: Activity? = null
    private var horizontalList: List<RecentOrder> = ArrayList<RecentOrder>()
    internal lateinit var movieItem: UserorderitemviewModelBinding

    fun setdata(movies: List<RecentOrder>, context: Activity) {
        this.horizontalList = movies
        this.context = context
        Log.e("date" , movies.size.toString())
        notifyDataSetChanged()
    }

    inner class MyViewHolder(designsBinding: UserorderitemviewModelBinding) : RecyclerView.ViewHolder(designsBinding.getRoot()) {


        internal var designsBinding: UserorderitemviewModelBinding
            internal set

        init {
            this.designsBinding = designsBinding
        }

        fun bind(design: UserordersViewModel) {
            designsBinding.model = design
            designsBinding.executePendingBindings()
        }

         fun getDesignsBinding(): UserorderitemviewModelBinding {
            return designsBinding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        movieItem = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_userorder, parent, false)
        return MyViewHolder(movieItem)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val designs = horizontalList[position]
        val design = UserordersViewModel()
        design.setdata(designs , context!!)
        holder.bind(design)
    }

    override fun getItemCount(): Int {
        return horizontalList.size
    }


}
