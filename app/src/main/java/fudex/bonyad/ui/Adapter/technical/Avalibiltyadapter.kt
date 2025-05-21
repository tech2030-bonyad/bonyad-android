package fudex.bonyad.ui.Adapter.technical

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import fudex.bonyad.R
import fudex.bonyad.databinding.AvailbiltyviewModelBinding
import fudex.bonyad.viewmodel.technical.AvailbiltyViewModel
import fudex.bonyad.Model.Availability
import java.util.*


/**
 * Created by سيد on 04/06/2017.
 */
class Avalibiltyadapter : RecyclerView.Adapter<Avalibiltyadapter.MyViewHolder>() {
    internal var context: Activity? = null
    private var horizontalList: List<Availability> = ArrayList<Availability>()
    internal lateinit var movieItem: AvailbiltyviewModelBinding

    fun setdata(movies: List<Availability>, context: Activity) {
        this.horizontalList = movies
        this.context = context
        Log.e("date" , movies.size.toString())
        notifyDataSetChanged()
    }

    inner class MyViewHolder(designsBinding: AvailbiltyviewModelBinding) : RecyclerView.ViewHolder(designsBinding.getRoot()) {


        internal var designsBinding: AvailbiltyviewModelBinding
            internal set

        init {
            this.designsBinding = designsBinding
        }

        fun bind(design: AvailbiltyViewModel) {
            designsBinding.model = design
            designsBinding.executePendingBindings()
        }

         fun getDesignsBinding(): AvailbiltyviewModelBinding {
            return designsBinding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        movieItem = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_availablity, parent, false)
        return MyViewHolder(movieItem)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val designs = horizontalList[position]
        val design = AvailbiltyViewModel()
        design.setdata(designs , context!!)
        holder.bind(design)
    }

    override fun getItemCount(): Int {
        return horizontalList.size
    }


}
