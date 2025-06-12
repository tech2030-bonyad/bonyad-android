package fudex.bonyad.ui.Fragment.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import fudex.bonyad.NetWorkConnction.DialogListener
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.databinding.UserordersfragmentviewModelBinding
import fudex.bonyad.ui.Activity.user.UserhomeActivity
import fudex.bonyad.viewmodel.user.OrdersListViewModel


class UserappointmentFragment : Fragment() {
    lateinit var ordersListViewModel: OrdersListViewModel
    lateinit var binding: UserordersfragmentviewModelBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return try {
            if (binding == null) {
                binding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.fragment_userappointment,
                    container,
                    false
                )
                ordersListViewModel = OrdersListViewModel(this@UserappointmentFragment)
                binding.model = ordersListViewModel
            }
            binding.root
        } catch (e: Exception) {
            binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_userappointment, container, false)
            ordersListViewModel = OrdersListViewModel(this@UserappointmentFragment)
            binding.model = ordersListViewModel
            return binding.root
        }
    }

    override fun onResume() {
        super.onResume()
        ordersListViewModel.page = 1
        ordersListViewModel.getorders()
        (activity as UserhomeActivity).userhomeViewModel.type.set(1)
    }


}