package fudex.bonyad.ui.Fragment.technical

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.databinding.TechnicalserviceviewModelBinding
import fudex.bonyad.ui.Activity.technical.TechnicalHomeActivity
import fudex.bonyad.viewmodel.technical.TechnicalserviceViewModel


class TechnicalservicesFragment : Fragment() {
    lateinit var technicalserviceViewModel: TechnicalserviceViewModel
    lateinit var binding: TechnicalserviceviewModelBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return try {
            if (binding == null) {
                binding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.fragment_technicalservices,
                    container,
                    false
                )
                technicalserviceViewModel = TechnicalserviceViewModel(this@TechnicalservicesFragment)
                binding.model = technicalserviceViewModel
            }
            binding.root
        } catch (e: Exception) {
            binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_technicalservices, container, false)
            technicalserviceViewModel = TechnicalserviceViewModel(this@TechnicalservicesFragment)
            binding.model = technicalserviceViewModel
            return binding.root
        }
    }

    override fun onResume() {
        super.onResume()
        technicalserviceViewModel.getservices()
        (activity as TechnicalHomeActivity).technicalhomeViewModel.type.set(2)
    }

}