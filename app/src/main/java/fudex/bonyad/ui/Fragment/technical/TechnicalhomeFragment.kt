package fudex.bonyad.ui.Fragment.technical

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.databinding.TechnicalhomefragmentViewModelBinding
import fudex.bonyad.ui.Activity.technical.TechnicalHomeActivity
import fudex.bonyad.ui.Activity.user.UserhomeActivity
import fudex.bonyad.viewmodel.technical.TechnicalhomefragmentViewModel


class TechnicalhomeFragment : Fragment() {
    lateinit var technicalhomefragmentViewModel: TechnicalhomefragmentViewModel
    lateinit var binding: TechnicalhomefragmentViewModelBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return try {
            if (binding == null) {
                binding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.fragment_technicalhome,
                    container,
                    false
                )
                technicalhomefragmentViewModel = TechnicalhomefragmentViewModel(this@TechnicalhomeFragment)
                binding.model = technicalhomefragmentViewModel
            }
            binding.root
        } catch (e: Exception) {
            binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_technicalhome, container, false)
            technicalhomefragmentViewModel = TechnicalhomefragmentViewModel(this@TechnicalhomeFragment)
            binding.model = technicalhomefragmentViewModel
            return binding.root
        }
    }

    override fun onResume() {
        super.onResume()
        technicalhomefragmentViewModel.gethome()
        (activity as TechnicalHomeActivity).technicalhomeViewModel.type.set(0)
    }


}