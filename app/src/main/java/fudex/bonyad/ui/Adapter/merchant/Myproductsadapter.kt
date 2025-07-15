package fudex.bonyad.ui.Adapter.merchant

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import fudex.bonyad.R
import fudex.bonyad.databinding.MyproductviewModelBinding
import fudex.bonyad.Model.ProductsDatum
import fudex.bonyad.viewmodel.merchant.ProductsViewModel
import java.util.*


/**
 * Created by سيد on 04/06/2017.
 */
class Myproductsadapter : RecyclerView.Adapter<Myproductsadapter.MyViewHolder>() {
    internal var context: Activity? = null
    private var horizontalList: List<ProductsDatum> = ArrayList<ProductsDatum>()
    internal lateinit var movieItem: MyproductviewModelBinding

    fun setdata(movies: List<ProductsDatum>, context: Activity) {
        this.horizontalList = movies
        this.context = context
        Log.e("date" , movies.size.toString())
        notifyDataSetChanged()
    }

    inner class MyViewHolder(designsBinding: MyproductviewModelBinding) : RecyclerView.ViewHolder(designsBinding.getRoot()) {


        internal var designsBinding: MyproductviewModelBinding
            internal set

        init {
            this.designsBinding = designsBinding
        }

        fun bind(design: ProductsViewModel) {
            designsBinding.model = design
            designsBinding.executePendingBindings()
        }

         fun getDesignsBinding(): MyproductviewModelBinding {
            return designsBinding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        movieItem = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_product, parent, false)
        return MyViewHolder(movieItem)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val designs = horizontalList[position]
        val design = ProductsViewModel()
        design.setdata(designs , context!!)
        holder.bind(design)
    }

    override fun getItemCount(): Int {
        return horizontalList.size
    }


}
