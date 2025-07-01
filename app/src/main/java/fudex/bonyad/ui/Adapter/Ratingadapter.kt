package fudex.bonyad.ui.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import fudex.bonyad.Model.RatingDatum
import fudex.bonyad.R
import fudex.bonyad.databinding.RatingViewBinding
import fudex.bonyad.viewmodel.RatingViewModel
import java.util.*


/**
 * Created by سيد on 04/06/2017.
 */
class Ratingadapter : RecyclerView.Adapter<Ratingadapter.MyViewHolder>() {
    internal var context: Activity? = null
    private var horizontalList: List<RatingDatum> = ArrayList<RatingDatum>()
    internal lateinit var movieItem: RatingViewBinding

    fun setdata(movies: List<RatingDatum>, context: Activity) {
        this.horizontalList = movies
        this.context = context
        Log.e("date" , movies.size.toString())
        notifyDataSetChanged()
    }

    inner class MyViewHolder(designsBinding: RatingViewBinding) : RecyclerView.ViewHolder(designsBinding.getRoot()) {


        internal var designsBinding: RatingViewBinding
            internal set

        init {
            this.designsBinding = designsBinding
        }

        fun bind(design: RatingViewModel) {
            designsBinding.model = design
            designsBinding.executePendingBindings()
        }

         fun getDesignsBinding(): RatingViewBinding {
            return designsBinding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        movieItem = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_rateing, parent, false)
        return MyViewHolder(movieItem)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val designs = horizontalList[position]
        val design = RatingViewModel()
        design.setdata(designs , context!!)
        holder.bind(design)
    }

    override fun getItemCount(): Int {
        return horizontalList.size
    }


}
