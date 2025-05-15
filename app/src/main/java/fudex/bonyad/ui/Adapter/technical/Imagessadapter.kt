package fudex.bonyad.ui.Adapter.technical

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import fudex.bonyad.R
import fudex.bonyad.databinding.ImagesviewModelBinding
import fudex.bonyad.viewmodel.technical.ImagesViewModel
import onnetysolutions.sadded.Model.RegisterimageModel
import java.util.*


/**
 * Created by سيد on 04/06/2017.
 */
class Imagessadapter : RecyclerView.Adapter<Imagessadapter.MyViewHolder>() {
    internal var context: Activity? = null
    private var horizontalList: List<RegisterimageModel> = ArrayList<RegisterimageModel>()
    internal lateinit var movieItem: ImagesviewModelBinding

    fun setdata(movies: List<RegisterimageModel>, context: Activity) {
        this.horizontalList = movies
        this.context = context
        Log.e("date" , movies.size.toString())
        notifyDataSetChanged()
    }

    inner class MyViewHolder(designsBinding: ImagesviewModelBinding) : RecyclerView.ViewHolder(designsBinding.getRoot()) {


        internal var designsBinding: ImagesviewModelBinding
            internal set

        init {
            this.designsBinding = designsBinding
        }

        fun bind(design: ImagesViewModel) {
            designsBinding.model = design
            designsBinding.executePendingBindings()
        }

         fun getDesignsBinding(): ImagesviewModelBinding {
            return designsBinding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        movieItem = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_image, parent, false)
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
