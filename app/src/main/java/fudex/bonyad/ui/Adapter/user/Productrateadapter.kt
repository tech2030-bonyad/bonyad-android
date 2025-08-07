package fudex.bonyad.ui.Adapter.user

import android.annotation.SuppressLint
import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import fudex.bonyad.Model.ProductElement
import fudex.bonyad.R
import fudex.bonyad.databinding.ProductrateviewModelBinding
import fudex.bonyad.viewmodel.user.ProductrateViewModel
import java.util.*


/**
 * Created by سيد on 04/06/2017.
 */
class Productrateadapter : RecyclerView.Adapter<Productrateadapter.MyViewHolder>() {
    internal var context: Activity? = null
    private var horizontalList: List<ProductElement> = ArrayList<ProductElement>()
    internal lateinit var movieItem: ProductrateviewModelBinding

    fun setdata(movies: List<ProductElement>, context: Activity) {
        this.horizontalList = movies
        this.context = context
        Log.e("date" , movies.size.toString())
        notifyDataSetChanged()
    }

    inner class MyViewHolder(designsBinding: ProductrateviewModelBinding) : RecyclerView.ViewHolder(designsBinding.getRoot()) {


        internal var designsBinding: ProductrateviewModelBinding
            internal set

        init {
            this.designsBinding = designsBinding
        }

        fun bind(design: ProductrateViewModel) {
            designsBinding.model = design
            designsBinding.executePendingBindings()
        }

         fun getDesignsBinding(): ProductrateviewModelBinding {
            return designsBinding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        movieItem = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_productrate, parent, false)
        return MyViewHolder(movieItem)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val designs = horizontalList[position]
        val design = ProductrateViewModel()
        design.setdata(designs , context!!)
        holder.bind(design)
        movieItem.rate.rating = horizontalList.get(position).rate!!.toFloat()

        movieItem.rate.setOnRatingBarChangeListener { _, rating, _ ->
            horizontalList.get(position).rate = rating.toString()
        }
        movieItem.comment.setText(horizontalList.get(position).comment)

        movieItem.comment.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                horizontalList.get(position).comment = s.toString()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun getItemCount(): Int {
        return horizontalList.size
    }


}
