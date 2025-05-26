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
import fudex.bonyad.databinding.TechnicailviewModelBinding
import fudex.bonyad.viewmodel.user.TechnicailViewModel
import fudex.bonyad.Model.Technician
import java.util.*


/**
 * Created by سيد on 04/06/2017.
 */
class Technicaladapter : RecyclerView.Adapter<Technicaladapter.MyViewHolder>() {
    internal var context: Fragment? = null
    private var horizontalList: List<Technician> = ArrayList<Technician>()
    internal lateinit var movieItem: TechnicailviewModelBinding

    fun setdata(movies: List<Technician>, context: Fragment) {
        this.horizontalList = movies
        this.context = context
        Log.e("date" , movies.size.toString())
        notifyDataSetChanged()
    }

    inner class MyViewHolder(designsBinding: TechnicailviewModelBinding) : RecyclerView.ViewHolder(designsBinding.getRoot()) {


        internal var designsBinding: TechnicailviewModelBinding
            internal set

        init {
            this.designsBinding = designsBinding
        }

        fun bind(design: TechnicailViewModel) {
            designsBinding.model = design
            designsBinding.executePendingBindings()
        }

         fun getDesignsBinding(): TechnicailviewModelBinding {
            return designsBinding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        movieItem = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_special, parent, false)
        return MyViewHolder(movieItem)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val designs = horizontalList[position]
        val design = TechnicailViewModel()
        design.setdata(designs , context!!)
        holder.bind(design)
    }

    override fun getItemCount(): Int {
        return horizontalList.size
    }


}
