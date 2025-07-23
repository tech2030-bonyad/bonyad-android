package fudex.bonyad.ui.Fragment.merchant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.MerchantordersfragmentviewModelBinding
import fudex.bonyad.ui.Activity.merchant.MerchanthomeActivity
import fudex.bonyad.ui.Activity.user.UserhomeActivity
import fudex.bonyad.ui.Fragment.user.UserappointmentFragment
import fudex.bonyad.viewmodel.merchant.OrdersListViewModel

class MerchantordersFragment : Fragment() {
    lateinit var ordersListViewModel: OrdersListViewModel
    lateinit var binding: MerchantordersfragmentviewModelBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return try {
            if (binding == null) {
                binding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.fragment_merchantorders,
                    container,
                    false
                )
                ordersListViewModel = OrdersListViewModel(this@MerchantordersFragment)
                binding.model = ordersListViewModel
            }
            binding.root
        } catch (e: Exception) {
            binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_merchantorders, container, false)
            ordersListViewModel = OrdersListViewModel(this@MerchantordersFragment)
            binding.model = ordersListViewModel
            return binding.root
        }
    }

    override fun onResume() {
        super.onResume()
        ordersListViewModel.page = 1
        ordersListViewModel.getorders()
        (activity as MerchanthomeActivity).merchanthomeViewModel.type.set(2)
    }

}