package fudex.bonyad.ui.Adapter.technical

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fudex.bonyad.Model.StatesDatum
import fudex.bonyad.R
import fudex.bonyad.databinding.Service1viewModelBinding
import fudex.bonyad.viewmodel.technical.Service1ViewModel
import java.util.*


/**
 * Created by سيد on 04/06/2017.
 */
class Service1adapter : RecyclerView.Adapter<Service1adapter.MyViewHolder>() {
    internal var context: Activity? = null
    private var horizontalList: List<StatesDatum> = ArrayList<StatesDatum>()
    internal lateinit var movieItem: Service1viewModelBinding
    internal var expandedStates:BooleanArray? = null

    fun setdata(movies: List<StatesDatum>, context: Activity) {
        this.horizontalList = movies
        this.context = context
        expandedStates = BooleanArray(movies.size) { false }
        Log.e("date" , movies.size.toString())
        notifyDataSetChanged()
    }

    inner class MyViewHolder(designsBinding: Service1viewModelBinding) : RecyclerView.ViewHolder(designsBinding.getRoot()) {


        internal var designsBinding: Service1viewModelBinding
            internal set

        init {
            this.designsBinding = designsBinding
        }

        fun bind(design: Service1ViewModel) {
            designsBinding.model = design
            designsBinding.executePendingBindings()
        }

         fun getDesignsBinding(): Service1viewModelBinding {
            return designsBinding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        movieItem = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_services1, parent, false)
        return MyViewHolder(movieItem)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val designs = horizontalList[position]
        val design = Service1ViewModel()
        design.setdata(designs , context!!)
        holder.bind(design)
        val text = Html.fromHtml(horizontalList[position].description?.replace(Regex("\n"), ""))
        val isExpanded = expandedStates!![position]
        holder.designsBinding.des.text = ""
        if (text.length > 80 && !isExpanded) {
            // Truncate and append "Load More"
            var truncatedText:SpannableString = SpannableString("")
            if (text.length > 80){
                truncatedText = SpannableString("${text.substring(0,80)}... ${context!!.getString(R.string.load_more)}")
            }else {
                truncatedText = SpannableString("${text.substring(0,text.length-10)}... ${context!!.getString(R.string.load_more)}")
            }
            val spannableString = truncatedText
            // Create clickable span for "Load More"
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    expandedStates!![position] = true
                    notifyDataSetChanged()
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false // Remove underline
                }
            }

            // Set the clickable and colored span for "Load More"
            val loadMoreStart = truncatedText.length - context!!.getString(R.string.load_more).length
            truncatedText.setSpan(clickableSpan, loadMoreStart, truncatedText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            truncatedText.setSpan(ForegroundColorSpan(Color.parseColor("#0080E0")), loadMoreStart, truncatedText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            // Update text view
            holder.designsBinding.des.text = spannableString
        } else if (isExpanded) {
            // Expanded case: append "Show Less"
            val spannableString = SpannableString("$text ${context!!.getString(R.string.show_less)}")

            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    expandedStates!![position] = false
                    notifyDataSetChanged()
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false // Remove underline
                }
            }

            // Set the clickable and colored span for "Show Less"
            val showLessStart = spannableString.length - context!!.getString(R.string.show_less).length
            spannableString.setSpan(clickableSpan, showLessStart, spannableString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#0080E0")), showLessStart, spannableString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            // Update text view
            holder.designsBinding.des.text = spannableString
        } else {
            // If text is not truncated, just set the original text
            holder.designsBinding.des.text = text?.replace(Regex("\n"),"")
        }
        holder.designsBinding.des.movementMethod = LinkMovementMethod.getInstance() // Enable clicking
    }

    override fun getItemCount(): Int {
        return horizontalList.size
    }


}
