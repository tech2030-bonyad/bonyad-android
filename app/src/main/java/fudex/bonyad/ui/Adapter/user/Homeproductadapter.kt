package fudex.bonyad.ui.Adapter.user

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fudex.bonyad.R
import fudex.bonyad.databinding.HomeproductviewModelBinding
import fudex.bonyad.viewmodel.user.HomeproductViewModel
import fudex.bonyad.Model.ProductsDatum
import java.util.*


/**
 * Created by سيد on 04/06/2017.
 */
class Homeproductadapter : RecyclerView.Adapter<Homeproductadapter.MyViewHolder>() {
    internal var context: Fragment? = null
    private var horizontalList: List<ProductsDatum> = ArrayList<ProductsDatum>()
    internal lateinit var movieItem: HomeproductviewModelBinding

    fun setdata(movies: List<ProductsDatum>, context: Fragment) {
        this.horizontalList = movies
        this.context = context
        Log.e("date" , movies.size.toString())
        notifyDataSetChanged()
    }

    inner class MyViewHolder(designsBinding: HomeproductviewModelBinding) : RecyclerView.ViewHolder(designsBinding.getRoot()) {


        internal var designsBinding: HomeproductviewModelBinding
            internal set

        init {
            this.designsBinding = designsBinding
        }

        fun bind(design: HomeproductViewModel) {
            designsBinding.model = design
            designsBinding.executePendingBindings()
        }

         fun getDesignsBinding(): HomeproductviewModelBinding {
            return designsBinding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        movieItem = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_homeproduct, parent, false)
        return MyViewHolder(movieItem)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val designs = horizontalList[position]
        val design = HomeproductViewModel()
        design.setdata(designs , context!!)
        holder.bind(design)
    }

    override fun getItemCount(): Int {
        return horizontalList.size
    }


}
