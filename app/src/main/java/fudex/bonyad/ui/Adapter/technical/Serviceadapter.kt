package fudex.bonyad.ui.Adapter.technical

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fudex.bonyad.Model.StatesDatum
import fudex.bonyad.R
import fudex.bonyad.databinding.ServiceviewModelBinding
import fudex.bonyad.viewmodel.technical.ServiceViewModel
import java.util.*


/**
 * Created by سيد on 04/06/2017.
 */
class Serviceadapter : RecyclerView.Adapter<Serviceadapter.MyViewHolder>() {
    internal var context: Fragment? = null
    private var horizontalList: List<StatesDatum> = ArrayList<StatesDatum>()
    internal lateinit var movieItem: ServiceviewModelBinding

    fun setdata(movies: List<StatesDatum>, context: Fragment) {
        this.horizontalList = movies
        this.context = context
        Log.e("date" , movies.size.toString())
        notifyDataSetChanged()
    }

    inner class MyViewHolder(designsBinding: ServiceviewModelBinding) : RecyclerView.ViewHolder(designsBinding.getRoot()) {


        internal var designsBinding: ServiceviewModelBinding
            internal set

        init {
            this.designsBinding = designsBinding
        }

        fun bind(design: ServiceViewModel) {
            designsBinding.model = design
            designsBinding.executePendingBindings()
        }

         fun getDesignsBinding(): ServiceviewModelBinding {
            return designsBinding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        movieItem = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_services, parent, false)
        return MyViewHolder(movieItem)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val designs = horizontalList[position]
        val design = ServiceViewModel()
        design.setdata(designs , context!!)
        holder.bind(design)
    }

    override fun getItemCount(): Int {
        return horizontalList.size
    }


}
