package fudex.bonyad.ui.Adapter.user

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fudex.bonyad.R
import fudex.bonyad.databinding.CatogaryviewModelBinding
import fudex.bonyad.viewmodel.user.DepViewModel
import fudex.bonyad.Model.StatesDatum
import java.util.*


/**
 * Created by سيد on 04/06/2017.
 */
class Depadapter : RecyclerView.Adapter<Depadapter.MyViewHolder>() {
    internal var context: Fragment? = null
    private var horizontalList: List<StatesDatum> = ArrayList<StatesDatum>()
    internal lateinit var movieItem: CatogaryviewModelBinding

    fun setdata(movies: List<StatesDatum>, context: Fragment) {
        this.horizontalList = movies
        this.context = context
        Log.e("date" , movies.size.toString())
        notifyDataSetChanged()
    }

    inner class MyViewHolder(designsBinding: CatogaryviewModelBinding) : RecyclerView.ViewHolder(designsBinding.getRoot()) {


        internal var designsBinding: CatogaryviewModelBinding
            internal set

        init {
            this.designsBinding = designsBinding
        }

        fun bind(design: DepViewModel) {
            designsBinding.model = design
            designsBinding.executePendingBindings()
        }

         fun getDesignsBinding(): CatogaryviewModelBinding {
            return designsBinding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        movieItem = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_dep, parent, false)
        return MyViewHolder(movieItem)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val designs = horizontalList[position]
        val design = DepViewModel()
        design.setdata(designs , context!!)
        holder.bind(design)
    }

    override fun getItemCount(): Int {
        return horizontalList.size
    }


}
