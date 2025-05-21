package fudex.bonyad.ui.Fragment.technical

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fudex.bonyad.R
import fudex.bonyad.ui.Activity.technical.TechnicalHomeActivity


class TechnicalhomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_technicalhome, container, false)
    }

    override fun onResume() {
        super.onResume()
        (activity as TechnicalHomeActivity).technicalhomeViewModel.type.set(0)
    }

}