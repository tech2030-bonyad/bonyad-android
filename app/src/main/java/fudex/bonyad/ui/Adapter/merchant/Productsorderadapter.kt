package fudex.bonyad.ui.Adapter.merchant

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import fudex.bonyad.R
import fudex.bonyad.databinding.ProductorderviewModelBinding
import fudex.bonyad.Model.ProductElement
import fudex.bonyad.viewmodel.merchant.ProductorderViewModel
import java.util.*


/**
 * Created by سيد on 04/06/2017.
 */
class Productsorderadapter : RecyclerView.Adapter<Productsorderadapter.MyViewHolder>() {
    internal var context: Activity? = null
    private var horizontalList: List<ProductElement> = ArrayList<ProductElement>()
    internal lateinit var movieItem: ProductorderviewModelBinding

    fun setdata(movies: List<ProductElement>, context: Activity) {
        this.horizontalList = movies
        this.context = context
        Log.e("date" , movies.size.toString())
        notifyDataSetChanged()
    }

    inner class MyViewHolder(designsBinding: ProductorderviewModelBinding) : RecyclerView.ViewHolder(designsBinding.getRoot()) {


        internal var designsBinding: ProductorderviewModelBinding
            internal set

        init {
            this.designsBinding = designsBinding
        }

        fun bind(design: ProductorderViewModel) {
            designsBinding.model = design
            designsBinding.executePendingBindings()
        }

         fun getDesignsBinding(): ProductorderviewModelBinding {
            return designsBinding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        movieItem = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_productorder, parent, false)
        return MyViewHolder(movieItem)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val designs = horizontalList[position]
        val design = ProductorderViewModel()
        design.setdata(designs , context!!)
        holder.bind(design)
    }

    override fun getItemCount(): Int {
        return horizontalList.size
    }


}
