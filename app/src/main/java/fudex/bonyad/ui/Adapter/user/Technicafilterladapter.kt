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
import fudex.bonyad.databinding.TechnicailservicesviewModelBinding
import fudex.bonyad.viewmodel.user.TechnicalservicesViewModel
import fudex.bonyad.Model.StatesDatum
import java.util.*


/**
 * Created by سيد on 04/06/2017.
 */
class Technicafilterladapter : RecyclerView.Adapter<Technicafilterladapter.MyViewHolder>() {
    internal var context: Fragment? = null
    private var horizontalList: List<StatesDatum> = ArrayList<StatesDatum>()
    internal lateinit var movieItem: TechnicailservicesviewModelBinding

    fun setdata(movies: List<StatesDatum>, context: Fragment) {
        this.horizontalList = movies
        this.context = context
        Log.e("date" , movies.size.toString())
        notifyDataSetChanged()
    }

    inner class MyViewHolder(designsBinding: TechnicailservicesviewModelBinding) : RecyclerView.ViewHolder(designsBinding.getRoot()) {


        internal var designsBinding: TechnicailservicesviewModelBinding
            internal set

        init {
            this.designsBinding = designsBinding
        }

        fun bind(design: TechnicalservicesViewModel) {
            designsBinding.model = design
            designsBinding.executePendingBindings()
        }

         fun getDesignsBinding(): TechnicailservicesviewModelBinding {
            return designsBinding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        movieItem = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_technicalservice, parent, false)
        return MyViewHolder(movieItem)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val designs = horizontalList[position]
        val design = TechnicalservicesViewModel()
        design.setdata(designs , context!!)
        holder.bind(design)
    }

    override fun getItemCount(): Int {
        return horizontalList.size
    }


}
