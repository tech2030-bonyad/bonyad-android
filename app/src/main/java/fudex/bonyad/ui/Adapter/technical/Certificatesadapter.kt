package fudex.bonyad.ui.Adapter.technical

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import fudex.bonyad.Model.Certificate
import fudex.bonyad.R
import fudex.bonyad.databinding.CertificatviewModelBinding
import fudex.bonyad.viewmodel.technical.CertificatViewModel
import java.util.*


/**
 * Created by سيد on 04/06/2017.
 */
class Certificatesadapter : RecyclerView.Adapter<Certificatesadapter.MyViewHolder>() {
    internal var context: Activity? = null
    private var horizontalList: List<Certificate> = ArrayList<Certificate>()
    internal lateinit var movieItem: CertificatviewModelBinding

    fun setdata(movies: List<Certificate>, context: Activity) {
        this.horizontalList = movies
        this.context = context
        Log.e("date" , movies.size.toString())
        notifyDataSetChanged()
    }

    inner class MyViewHolder(designsBinding: CertificatviewModelBinding) : RecyclerView.ViewHolder(designsBinding.getRoot()) {


        internal var designsBinding: CertificatviewModelBinding
            internal set

        init {
            this.designsBinding = designsBinding
        }

        fun bind(design: CertificatViewModel) {
            designsBinding.model = design
            designsBinding.executePendingBindings()
        }

         fun getDesignsBinding(): CertificatviewModelBinding {
            return designsBinding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        movieItem = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_certificate, parent, false)
        return MyViewHolder(movieItem)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       try {
           val designs = horizontalList[position]
           val design = CertificatViewModel()
           design.setdata(designs , context!!)
           holder.bind(design)
       }catch (e:Exception){
           val design = CertificatViewModel()
           design.setdata(Certificate(0,null,null,null) , context!!)
           holder.bind(design)
       }
    }

    override fun getItemCount(): Int {
        return horizontalList.size + 1
    }


}
