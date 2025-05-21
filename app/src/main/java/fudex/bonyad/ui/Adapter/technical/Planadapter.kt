package fudex.bonyad.ui.Adapter.technical

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import fudex.bonyad.R
import fudex.bonyad.databinding.PlanViewBinding
import fudex.bonyad.viewmodel.technical.PlanViewModel
import fudex.bonyad.Model.PlanData
import java.util.*


/**
 * Created by سيد on 04/06/2017.
 */
class Planadapter : RecyclerView.Adapter<Planadapter.MyViewHolder>() {
    internal var context: Activity? = null
    private var horizontalList: List<PlanData> = ArrayList<PlanData>()
    internal lateinit var movieItem: PlanViewBinding

    fun setdata(movies: List<PlanData>, context: Activity) {
        this.horizontalList = movies
        this.context = context
        Log.e("date" , movies.size.toString())
        notifyDataSetChanged()
    }

    inner class MyViewHolder(designsBinding: PlanViewBinding) : RecyclerView.ViewHolder(designsBinding.getRoot()) {


        internal var designsBinding: PlanViewBinding
            internal set

        init {
            this.designsBinding = designsBinding
        }

        fun bind(design: PlanViewModel) {
            designsBinding.model = design
            designsBinding.executePendingBindings()
        }

         fun getDesignsBinding(): PlanViewBinding {
            return designsBinding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        movieItem = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_packages, parent, false)
        return MyViewHolder(movieItem)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val designs = horizontalList[position]
        val design = PlanViewModel()
        design.setdata(designs , context!!)
        holder.bind(design)
    }

    override fun getItemCount(): Int {
        return horizontalList.size
    }

}
