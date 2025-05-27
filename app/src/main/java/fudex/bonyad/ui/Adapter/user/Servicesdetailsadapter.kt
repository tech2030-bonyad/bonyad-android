package fudex.bonyad.ui.Adapter.user

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import fudex.bonyad.Model.StatesDatum
import fudex.bonyad.R
import fudex.bonyad.databinding.ServicedetailsviewModelBinding
import fudex.bonyad.viewmodel.user.ServicedetailsViewModel
import java.util.*


/**
 * Created by سيد on 04/06/2017.
 */
class Servicesdetailsadapter : RecyclerView.Adapter<Servicesdetailsadapter.MyViewHolder>() {
    internal var context: Activity? = null
    private var horizontalList: List<StatesDatum> = ArrayList<StatesDatum>()
    internal lateinit var movieItem: ServicedetailsviewModelBinding

    fun setdata(movies: List<StatesDatum>, context: Activity) {
        this.horizontalList = movies
        this.context = context
        Log.e("date" , movies.size.toString())
        notifyDataSetChanged()
    }

    inner class MyViewHolder(designsBinding: ServicedetailsviewModelBinding) : RecyclerView.ViewHolder(designsBinding.getRoot()) {


        internal var designsBinding: ServicedetailsviewModelBinding
            internal set

        init {
            this.designsBinding = designsBinding
        }

        fun bind(design: ServicedetailsViewModel) {
            designsBinding.model = design
            designsBinding.executePendingBindings()
        }

         fun getDesignsBinding(): ServicedetailsviewModelBinding {
            return designsBinding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        movieItem = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_servicesdetails, parent, false)
        return MyViewHolder(movieItem)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val designs = horizontalList[position]
        val design = ServicedetailsViewModel()
        design.setdata(designs , context!!)
        holder.bind(design)
    }

    override fun getItemCount(): Int {
        return horizontalList.size
    }


}
