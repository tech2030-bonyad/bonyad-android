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
import fudex.bonyad.databinding.Technicail1viewModelBinding
import fudex.bonyad.viewmodel.user.Technicail1ViewModel
import fudex.bonyad.Model.Technician
import java.util.*


/**
 * Created by سيد on 04/06/2017.
 */
class Technical1adapter : RecyclerView.Adapter<Technical1adapter.MyViewHolder>() {
    internal var context: Activity? = null
    private var horizontalList: List<Technician> = ArrayList<Technician>()
    internal lateinit var movieItem: Technicail1viewModelBinding

    fun setdata(movies: List<Technician>, context: Activity) {
        this.horizontalList = movies
        this.context = context
        Log.e("date" , movies.size.toString())
        notifyDataSetChanged()
    }

    inner class MyViewHolder(designsBinding: Technicail1viewModelBinding) : RecyclerView.ViewHolder(designsBinding.getRoot()) {


        internal var designsBinding: Technicail1viewModelBinding
            internal set

        init {
            this.designsBinding = designsBinding
        }

        fun bind(design: Technicail1ViewModel) {
            designsBinding.model = design
            designsBinding.executePendingBindings()
        }

         fun getDesignsBinding(): Technicail1viewModelBinding {
            return designsBinding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        movieItem = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_special1, parent, false)
        return MyViewHolder(movieItem)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val designs = horizontalList[position]
        val design = Technicail1ViewModel()
        design.setdata(designs , context!!,movieItem)
        holder.bind(design)
    }

    override fun getItemCount(): Int {
        return horizontalList.size
    }


}
