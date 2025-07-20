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
import fudex.bonyad.databinding.ProductsitemviewModelBinding
import fudex.bonyad.viewmodel.user.ProductitemViewModel
import fudex.bonyad.Model.ProductsDatum
import java.util.*


/**
 * Created by سيد on 04/06/2017.
 */
class Productitemadapter : RecyclerView.Adapter<Productitemadapter.MyViewHolder>() {
    internal var context: Activity? = null
    private var horizontalList: List<ProductsDatum> = ArrayList<ProductsDatum>()
    internal lateinit var movieItem: ProductsitemviewModelBinding

    fun setdata(movies: List<ProductsDatum>, context: Activity) {
        this.horizontalList = movies
        this.context = context
        Log.e("date" , movies.size.toString())
        notifyDataSetChanged()
    }

    inner class MyViewHolder(designsBinding: ProductsitemviewModelBinding) : RecyclerView.ViewHolder(designsBinding.getRoot()) {


        internal var designsBinding: ProductsitemviewModelBinding
            internal set

        init {
            this.designsBinding = designsBinding
        }

        fun bind(design: ProductitemViewModel) {
            designsBinding.model = design
            designsBinding.executePendingBindings()
        }

         fun getDesignsBinding(): ProductsitemviewModelBinding {
            return designsBinding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        movieItem = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_homeproduct1, parent, false)
        return MyViewHolder(movieItem)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val designs = horizontalList[position]
        val design = ProductitemViewModel()
        design.setdata(designs , context!!)
        holder.bind(design)
    }

    override fun getItemCount(): Int {
        return horizontalList.size
    }


}
