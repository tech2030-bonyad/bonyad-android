package fudex.bonyad.ui.Fragment.technical

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import fudex.bonyad.NetWorkConnction.DialogListener
import fudex.bonyad.R
import fudex.bonyad.databinding.TechnicalordersfragmentviewModelBinding
import fudex.bonyad.ui.Activity.technical.TechnicalHomeActivity
import fudex.bonyad.ui.Activity.user.UserhomeActivity
import fudex.bonyad.viewmodel.technical.OrdersListViewModel

class TechnicalappointmentFragment : Fragment(),DialogListener {
    lateinit var ordersListViewModel: OrdersListViewModel
    lateinit var binding: TechnicalordersfragmentviewModelBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return try {
            if (binding == null) {
                binding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.fragment_technicalappointment,
                    container,
                    false
                )
                ordersListViewModel = OrdersListViewModel(this@TechnicalappointmentFragment)
                binding.model = ordersListViewModel
            }
            binding.root
        } catch (e: Exception) {
            binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_technicalappointment, container, false)
            ordersListViewModel = OrdersListViewModel(this@TechnicalappointmentFragment)
            binding.model = ordersListViewModel
            return binding.root
        }
    }

    override fun onResume() {
        super.onResume()
        ordersListViewModel.page = 1
        ordersListViewModel.getorders()
        (activity as TechnicalHomeActivity).technicalhomeViewModel.type.set(1)
    }

    override fun onDataReceived(data: String) {
        if(data.contains("refuse")){
            ordersListViewModel.refuseorder(data.replace("refuse",""))
        }
    }

}