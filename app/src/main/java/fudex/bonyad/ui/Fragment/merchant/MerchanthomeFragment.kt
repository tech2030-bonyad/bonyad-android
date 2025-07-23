package fudex.bonyad.ui.Fragment.merchant

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.MerchanthomefragmentViewModelBinding
import fudex.bonyad.ui.Activity.merchant.MerchanthomeActivity
import fudex.bonyad.ui.Activity.technical.TechnicalHomeActivity
import fudex.bonyad.ui.Fragment.technical.TechnicalhomeFragment
import fudex.bonyad.viewmodel.merchant.MerchanthomefragmentViewModel

class MerchanthomeFragment : Fragment() {

    lateinit var merchanthomefragmentViewModel: MerchanthomefragmentViewModel
    lateinit var binding: MerchanthomefragmentViewModelBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return try {
            if (binding == null) {
                binding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.fragment_merchanthome,
                    container,
                    false
                )
                merchanthomefragmentViewModel = MerchanthomefragmentViewModel(this@MerchanthomeFragment)
                binding.model = merchanthomefragmentViewModel
            }
            binding.root
        } catch (e: Exception) {
            binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_merchanthome, container, false)
            merchanthomefragmentViewModel = MerchanthomefragmentViewModel(this@MerchanthomeFragment)
            binding.model = merchanthomefragmentViewModel
            return binding.root
        }
    }

    override fun onResume() {
        super.onResume()
        merchanthomefragmentViewModel.gethome()
        (activity as MerchanthomeActivity).merchanthomeViewModel.type.set(0)
        binding.root!!.isFocusableInTouchMode = true
        binding.root!!.requestFocus()
        binding.root!!.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                activity?.finish()
                return@OnKeyListener true
            }
            false
        })
    }

}