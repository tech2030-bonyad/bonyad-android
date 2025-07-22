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
import fudex.bonyad.databinding.CartproductviewModelBinding
import fudex.bonyad.viewmodel.user.CartproductViewModel
import fudex.bonyad.Model.ProductElement
import java.util.*


/**
 * Created by سيد on 04/06/2017.
 */
class Cartproductadapter : RecyclerView.Adapter<Cartproductadapter.MyViewHolder>() {
    internal var context: Activity? = null
    private var horizontalList: List<ProductElement> = ArrayList<ProductElement>()
    internal lateinit var movieItem: CartproductviewModelBinding

    fun setdata(movies: List<ProductElement>, context: Activity) {
        this.horizontalList = movies
        this.context = context
        Log.e("date" , movies.size.toString())
        notifyDataSetChanged()
    }

    inner class MyViewHolder(designsBinding: CartproductviewModelBinding) : RecyclerView.ViewHolder(designsBinding.getRoot()) {


        internal var designsBinding: CartproductviewModelBinding
            internal set

        init {
            this.designsBinding = designsBinding
        }

        fun bind(design: CartproductViewModel) {
            designsBinding.model = design
            designsBinding.executePendingBindings()
        }

         fun getDesignsBinding(): CartproductviewModelBinding {
            return designsBinding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        movieItem = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_cart, parent, false)
        return MyViewHolder(movieItem)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val designs = horizontalList[position]
        val design = CartproductViewModel()
        design.setdata(designs , context!!)
        holder.bind(design)
    }

    override fun getItemCount(): Int {
        return horizontalList.size
    }


}
