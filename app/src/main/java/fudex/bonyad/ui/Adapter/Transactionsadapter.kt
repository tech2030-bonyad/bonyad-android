package fudex.bonyad.ui.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import fudex.bonyad.Model.TransactionsDatum
import fudex.bonyad.R
import fudex.bonyad.databinding.TransactionsviewModelBinding
import fudex.bonyad.viewmodel.TransactionsViewModel
import java.util.*


/**
 * Created by سيد on 04/06/2017.
 */
class Transactionsadapter : RecyclerView.Adapter<Transactionsadapter.MyViewHolder>() {
    internal var context: Activity? = null
    private var horizontalList: List<TransactionsDatum> = ArrayList<TransactionsDatum>()
    internal lateinit var movieItem: TransactionsviewModelBinding

    fun setdata(movies: List<TransactionsDatum>, context: Activity) {
        this.horizontalList = movies
        this.context = context
        Log.e("date" , movies.size.toString())
        notifyDataSetChanged()
    }

    inner class MyViewHolder(designsBinding: TransactionsviewModelBinding) : RecyclerView.ViewHolder(designsBinding.getRoot()) {


        internal var designsBinding: TransactionsviewModelBinding
            internal set

        init {
            this.designsBinding = designsBinding
        }

        fun bind(design: TransactionsViewModel) {
            designsBinding.model = design
            designsBinding.executePendingBindings()
        }

         fun getDesignsBinding(): TransactionsviewModelBinding {
            return designsBinding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        movieItem = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_wallet, parent, false)
        return MyViewHolder(movieItem)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val designs = horizontalList[position]
        val design = TransactionsViewModel()
        design.setdata(designs , context!!)
        holder.bind(design)
    }

    override fun getItemCount(): Int {
        return horizontalList.size
    }


}
