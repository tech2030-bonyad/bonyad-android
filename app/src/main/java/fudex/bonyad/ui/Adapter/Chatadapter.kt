package fudex.bonyad.ui.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import fudex.bonyad.Model.MessageItem
import fudex.bonyad.R
import fudex.bonyad.databinding.ChatviewModelBinding
import fudex.bonyad.viewmodel.ChatViewModel
import java.util.*


/**
 * Created by سيد on 04/06/2017.
 */
class Chatadapter : RecyclerView.Adapter<Chatadapter.MyViewHolder>() {
    internal var context: Activity? = null
    private var horizontalList: List<MessageItem> = ArrayList<MessageItem>()
    internal lateinit var movieItem: ChatviewModelBinding

    fun setdata(movies: List<MessageItem>, context: Activity) {
        this.horizontalList = movies
        this.context = context
        Log.e("date" , movies.size.toString())
        notifyDataSetChanged()
    }

    inner class MyViewHolder(designsBinding: ChatviewModelBinding) : RecyclerView.ViewHolder(designsBinding.getRoot()) {


        internal var designsBinding: ChatviewModelBinding
            internal set

        init {
            this.designsBinding = designsBinding
        }

        fun bind(design: ChatViewModel) {
            designsBinding.model = design
            designsBinding.executePendingBindings()
        }

         fun getDesignsBinding(): ChatviewModelBinding {
            return designsBinding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        movieItem = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_chat, parent, false)
        return MyViewHolder(movieItem)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val designs = horizontalList[position]
        val design = ChatViewModel()
        design.setdata(designs , context!!)
        holder.bind(design)
    }

    override fun getItemCount(): Int {
        return horizontalList.size
    }


}
