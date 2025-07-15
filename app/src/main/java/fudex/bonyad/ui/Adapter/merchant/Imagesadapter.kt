package fudex.bonyad.ui.Adapter.merchant

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import fudex.bonyad.Model.Certificate
import fudex.bonyad.R
import fudex.bonyad.databinding.ImagesmerchantviewModelBinding
import fudex.bonyad.viewmodel.technical.AvailbiltyViewModel
import fudex.bonyad.viewmodel.merchant.ImagesViewModel
import java.util.*


/**
 * Created by سيد on 04/06/2017.
 */
class Imagesadapter : RecyclerView.Adapter<Imagesadapter.MyViewHolder>() {
    internal var context: Activity? = null
    private var horizontalList: List<Certificate> = ArrayList<Certificate>()
    internal lateinit var movieItem: ImagesmerchantviewModelBinding

    fun setdata(movies: List<Certificate>, context: Activity) {
        this.horizontalList = movies
        this.context = context
        Log.e("date" , movies.size.toString())
        notifyDataSetChanged()
    }

    inner class MyViewHolder(designsBinding: ImagesmerchantviewModelBinding) : RecyclerView.ViewHolder(designsBinding.getRoot()) {


        internal var designsBinding: ImagesmerchantviewModelBinding
            internal set

        init {
            this.designsBinding = designsBinding
        }

        fun bind(design: ImagesViewModel) {
            designsBinding.model = design
            designsBinding.executePendingBindings()
        }

         fun getDesignsBinding(): ImagesmerchantviewModelBinding {
            return designsBinding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        movieItem = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_images, parent, false)
        return MyViewHolder(movieItem)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val designs = horizontalList[position]
        val design = ImagesViewModel()
        design.setdata(designs , context!!)
        holder.bind(design)
    }

    override fun getItemCount(): Int {
        return horizontalList.size 
    }


}
